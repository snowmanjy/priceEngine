package com.test.app.action;

import com.test.app.dto.Quote;

public class ChangeDiscountActionHandler extends QuoteActionHandler {

    private Double newDiscount;

    public ChangeDiscountActionHandler(Quote quote) {
        super(quote);
    }


    public void setDiscount(Double discount) {
        this.newDiscount = discount;
    }

    public Quote handle() {

        quote.setDiscount(newDiscount);
        return quote;
    }
}
