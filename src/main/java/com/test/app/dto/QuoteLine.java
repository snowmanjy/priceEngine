package com.test.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.redis.core.RedisHash;

@JsonIgnoreProperties(ignoreUnknown = true)
@RedisHash("quoteline")
public class QuoteLine {

    private int lineNumber;

    private Product product;

    private int quantity = 0;

    private Double discount = 0d;

    private Double totalPrice = 0d;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Double getTotalPrice() {
        totalPrice = 0d;
        if(product != null && quantity != 0) {
            totalPrice = (product.getPrice() * quantity) * (1 - discount);
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "QuoteLine{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", discount=" + discount +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
}
