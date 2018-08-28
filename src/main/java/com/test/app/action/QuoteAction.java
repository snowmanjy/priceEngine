package com.test.app.action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.app.dto.Quote;


public abstract class QuoteAction implements Action {

    protected Quote quote;

    protected ActionType actionType;

    @JsonCreator
    public QuoteAction(@JsonProperty("quote") Quote quote, @JsonProperty("actionType") ActionType actionType) {
        this.quote = quote;
        this.actionType = actionType;
    }

    public Quote getQuote() {
        return quote;
    }

    public ActionType getActionType() {
        return actionType;
    }

}
