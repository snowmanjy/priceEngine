package com.test.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.redis.core.RedisHash;

@JsonIgnoreProperties(ignoreUnknown = true)
@RedisHash("quoteline")
public class QuoteLine {

    private Product product;

    private int number = 0;

    private Double discount = 0d;

    private Double totalPrice = 0d;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotalPrice() {
        if(product != null && number != 0) {
            totalPrice = (product.getPrice() * number) * (1 - discount);
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "QuoteLine{" +
                "product=" + product +
                ", number=" + number +
                ", discount=" + discount +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
}
