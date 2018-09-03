package com.test.app.action;

import com.test.app.dto.Quote;

public class ChangeCouponActionHandler extends QuoteActionHandler {

    private Double coupon;

    public ChangeCouponActionHandler(Quote quote) {
        super(quote);
    }

    public Double getCoupon() {
        return coupon;
    }

    public void setCoupon(Double coupon) {
        this.coupon = coupon;
    }

    public Quote handle() {
        quote.setCoupon(coupon);
        return quote;
    }
}
