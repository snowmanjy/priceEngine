package com.test.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.app.action.ActionType;
import com.test.app.dto.QuoteLine;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteActionModel {

    private String quoteName;

    private QuoteLine quoteLine = new QuoteLine();

    private Double discount = 0d;

    private ActionType actionType;

    public String getQuoteName() {
        return quoteName;
    }

    public void setQuoteName(String quoteName) {
        this.quoteName = quoteName;
    }

    public QuoteLine getQuoteLine() {
        return quoteLine;
    }

    public void setQuoteLine(QuoteLine quoteLine) {
        this.quoteLine = quoteLine;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }
}
