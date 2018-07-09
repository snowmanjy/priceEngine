package com.test.app.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public StockDataGateway stockDataGateway() {
        return new StockDataGatewayImpl();
    }

}
