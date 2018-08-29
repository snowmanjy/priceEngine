package com.test.app.action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.app.dto.Quote;


public abstract class QuoteActionHandler implements ActionHandler {

    protected Quote quote;

    public QuoteActionHandler(Quote quote) {
        this.quote = quote;
    }

    public Quote getQuote() {
        return quote;
    }

}
