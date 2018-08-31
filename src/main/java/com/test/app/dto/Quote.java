package com.test.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@RedisHash("quote")
public class Quote {

    private String name;

    private List<QuoteLine> quoteLines = new ArrayList<QuoteLine>();

    private Double coupon = 0d;

    private Double discount = 0d;

    private Double shippingFee = 0d;

    private Double totalPrice = 0d;

    private Double targetPrice = 0d;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuoteLine> getQuoteLines() {
        return quoteLines;
    }

    public void setQuoteLines(List<QuoteLine> quoteLines) {
        this.quoteLines = quoteLines;
    }

    public Double getCoupon() {
        return coupon;
    }

    public void setCoupon(Double coupon) {
        this.coupon = coupon;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Double getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(Double targetPrice) {
        this.targetPrice = targetPrice;
    }

    public Double getTotalPrice() {
        totalPrice = 0d;
        if(quoteLines != null && quoteLines.size() > 0) {
            for(QuoteLine quoteLine : quoteLines) {
                totalPrice += quoteLine.getTotalPrice();
            }
        }

        totalPrice -= coupon;

        totalPrice *= 1 - discount;

        totalPrice += shippingFee;

        return totalPrice;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "name='" + name + '\'' +
                ", quoteLines=" + quoteLines +
                ", coupon=" + coupon +
                ", discount=" + discount +
                ", shippingFee=" + shippingFee +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
