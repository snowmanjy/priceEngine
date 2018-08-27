package com.test.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.redis.core.RedisHash;

@JsonIgnoreProperties(ignoreUnknown = true)
@RedisHash("product")
public class Product {

    private String productName;

    private Double price;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
