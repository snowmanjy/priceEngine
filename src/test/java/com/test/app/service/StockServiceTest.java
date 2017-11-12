package com.test.app.service;

import com.test.app.dao.StockDao;
import com.test.app.dto.Response;
import com.test.app.dto.Status;
import com.test.app.dto.StockDailyData;
import com.test.app.dto.StockData;
import com.test.app.web.StockNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {

    @Mock
    private StockDao stockDao;

    @InjectMocks
    private StockService stockService = new StockService();

    @Test(expected = StockNotFoundException.class)
    public void testGetMaxProfitDatesWithNullDataReturned() throws Exception {
        when(stockDao.getStockData(any(String.class), any(Date.class))).thenReturn(null);
        stockService.getMaxProfitDates("IBM");
    }

    @Test
    public void testGetMaxProfitDatesHappyPath() throws Exception {
        StockData data = new StockData();
        Status status = new Status();
        status.setCode(200);
        data.setStatus(status);
        StockDailyData[] results = new StockDailyData[1];
        StockDailyData dailyData = new StockDailyData();
        results[0] = dailyData;
        dailyData.setHigh(2d);
        dailyData.setTradingDay("20170101");
        data.setResults(results);
        when(stockDao.getStockData(any(String.class), any(Date.class))).thenReturn(data);
        Response response = stockService.getMaxProfitDates("IBM");
        Assert.assertEquals(200, response.getStatus().getCode());
        Assert.assertEquals("20170101", response.getBuyDate());
        Assert.assertEquals("20170101", response.getSellDate());
        Assert.assertEquals(0d, response.getMaxProfit(), 0.1d);

    }

    @Test
    public void testCalculateMaxDiffIndexs() throws Exception {
        double[] testData = {0, 1, 2, 3, 4};

        int[] result = stockService.calculateMaxDiffIndexs(testData);
        Assert.assertEquals(0, result[0]);
        Assert.assertEquals(4, result[1]);

        double[] testData1 = {4, 3, 2, 1, 0};
        result = stockService.calculateMaxDiffIndexs(testData1);
        Assert.assertEquals(0, result[0]);
        Assert.assertEquals(0, result[1]);

        double[] testData2 = {1, 1, 1, 1, 1};
        result = stockService.calculateMaxDiffIndexs(testData2);
        Assert.assertEquals(0, result[0]);
        Assert.assertEquals(0, result[1]);

        double[] testData3 = {1, 3, 0, 3, 6, 11};
        result = stockService.calculateMaxDiffIndexs(testData3);
        Assert.assertEquals(2, result[0]);
        Assert.assertEquals(5, result[1]);

        double[] testData4 = {10, 3, 0, 3, 6, 1, 4, 8, 5};
        result = stockService.calculateMaxDiffIndexs(testData4);
        Assert.assertEquals(2, result[0]);
        Assert.assertEquals(7, result[1]);

        double[] testData5 = {3, 8, 0, 3, 1, 4};
        result = stockService.calculateMaxDiffIndexs(testData5);
        Assert.assertEquals(0, result[0]);
        Assert.assertEquals(1, result[1]);

        double[] testData6 = {1, 1, 1, 1, 2, 2};
        result = stockService.calculateMaxDiffIndexs(testData6);
        Assert.assertEquals(0, result[0]);
        Assert.assertEquals(4, result[1]);

        double[] testData7 = {1};
        result = stockService.calculateMaxDiffIndexs(testData7);
        Assert.assertEquals(0, result[0]);
        Assert.assertEquals(0, result[1]);
    }
}
