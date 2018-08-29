package com.test.app.action;

import com.test.app.dto.Quote;
import com.test.app.dto.QuoteLine;

import java.util.ArrayList;

public class AddQuoteLineActionHandler extends QuoteActionHandler {

    private QuoteLine quoteLine;

    public AddQuoteLineActionHandler(Quote quote) {
        super(quote);
    }


    public void setQuoteLine(QuoteLine quoteLine) {
        this.quoteLine = quoteLine;
    }

    public void handle() {
        if(quote.getQuoteLines() == null) {
            quote.setQuoteLines(new ArrayList<QuoteLine>());
        }

        quote.getQuoteLines().add(quoteLine);
    }
}
