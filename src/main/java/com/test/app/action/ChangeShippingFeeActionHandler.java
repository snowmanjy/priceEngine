package com.test.app.action;

import com.test.app.dto.Quote;

public class ChangeShippingFeeActionHandler extends QuoteActionHandler {

    private Double newShippingFee;

    public ChangeShippingFeeActionHandler(Quote quote) {
        super(quote);
    }

    public Double getNewShippingFee() {
        return newShippingFee;
    }

    public void setNewShippingFee(Double newShippingFee) {
        this.newShippingFee = newShippingFee;
    }

    public void handle() {
        quote.setShippingFee(newShippingFee);
    }
}
