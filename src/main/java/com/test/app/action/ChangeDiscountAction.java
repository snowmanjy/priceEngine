package com.test.app.action;

import com.test.app.dto.Quote;

public class ChangeDiscountAction extends QuoteAction{

    private Double newDiscount;

    public ChangeDiscountAction(Quote quote, ActionType actionType) {
        super(quote, actionType);
    }


    public void setDiscount(Double discount) {
        this.newDiscount = discount;
    }

    public void execute() {
        quote.setDiscount(newDiscount);
    }
}
