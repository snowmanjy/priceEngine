package com.test.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockData {
    private StockDailyData[] results;
    private Status status;

    public StockDailyData[] getResults() {
        return results;
    }

    public void setResults(StockDailyData[] results) {
        this.results = results;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
