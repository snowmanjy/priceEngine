package com.test.app.service;

import com.google.common.annotations.VisibleForTesting;
import com.test.app.dao.StockDao;
import com.test.app.dto.Response;
import com.test.app.dto.StockDailyData;
import com.test.app.dto.StockData;
import com.test.app.web.StockNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class StockService {

	private static Logger logger = LoggerFactory.getLogger(StockService.class);

	@Value("${stock.data.days:180}")
	private int days = 180;

	@Autowired
	private StockDao stockDao;

	public Response getMaxProfitDates(String stock) throws StockNotFoundException {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1 * days);
        Date startDate = cal.getTime();

		StockData data = stockDao.getStockData(stock, startDate);

        Response response = new Response();

        if(data == null || data.getStatus() == null) {
            throw new StockNotFoundException(stock);

        }

		if(data.getStatus().getCode() != 200
                || data.getResults() == null || data.getResults().length == 0) {
            response.setStatus(data.getStatus());
            return response;

        }

		return calculateMaxProfitDates(data);
	}

	private Response calculateMaxProfitDates(StockData data) {
        StockDailyData[] dailyDataArray = data.getResults();
        Response response = new Response();
        response.setStatus(data.getStatus());

        double[] dailyHighArray = new double[dailyDataArray.length];

        for(int i = 0; i < dailyDataArray.length; i++) {
            dailyHighArray[i] = dailyDataArray[i].getHigh();
        }

        int[] tradingDays = calculateMaxDiffIndexs(dailyHighArray);
        response.setBuyDate(dailyDataArray[tradingDays[0]].getTradingDay());
        response.setSellDate(dailyDataArray[tradingDays[1]].getTradingDay());
        response.setMaxProfit(dailyDataArray[tradingDays[1]].getHigh() - dailyDataArray[tradingDays[0]].getHigh());

        return response;
    }

    @VisibleForTesting
    protected int[] calculateMaxDiffIndexs(double[] doubleArray) {
	    // indexArrays[0]: index of lowest value
        // indexArrays[1]: index of highest value

	    int[] indexArrays = new int[2];
        double lowest, highest;
        lowest = highest = doubleArray[0];
        double[] profit = new double[doubleArray.length];
        int[] currentLowestIndex = new int[doubleArray.length];


        for(int i = 0; i < doubleArray.length; i++) {
            if(doubleArray[i] < lowest) {
                lowest = doubleArray[i];
                indexArrays[0] = i;
            } else {
                if(doubleArray[i] > highest) {
                    highest = doubleArray[i];
                }

                profit[i] = doubleArray[i] - lowest;
                currentLowestIndex[i] = indexArrays[0];
            }
        }

        double currentProfit = profit[0];
        indexArrays[0] = 0;
        for(int i = 0; i < profit.length; i++) {
            if(profit[i] > currentProfit) {
                currentProfit = profit[i];
                indexArrays[0] = currentLowestIndex[i];
                indexArrays[1] = i;
            }
        }

        return indexArrays;
    }
}
