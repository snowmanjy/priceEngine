package com.test.app.gateway;

import com.test.app.dto.StockData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;


public class StockDataGatewayImpl implements StockDataGateway{

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("YYYYMMdd");

    @Value("${stock.data.url:}")
    private String url;

    public StockData getStockDailyData(String stock, Date startDate) {

        String startDateString = DATE_FORMAT.format(startDate);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("startDate", startDateString)
                .queryParam("symbol", stock);

        RestTemplate restTemplate = new RestTemplate();
        StockData data = restTemplate.getForObject(builder.build().encode().toUri(), com.test.app.dto.StockData.class);

        return data;
    }
}
