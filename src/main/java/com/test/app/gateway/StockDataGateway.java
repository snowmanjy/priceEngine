package com.test.app.gateway;

import com.test.app.dto.StockData;

import java.util.Date;

public interface StockDataGateway {
    StockData getStockDailyData(String stock, Date startDate);
}
