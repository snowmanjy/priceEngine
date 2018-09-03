package com.test.app.action;

import com.test.app.dto.Quote;

public class CreateQuoteActionHandler extends QuoteActionHandler {

    public CreateQuoteActionHandler(Quote quote) {
        super(quote);
    }

    public Quote handle() {
        return quote;
    }
}
