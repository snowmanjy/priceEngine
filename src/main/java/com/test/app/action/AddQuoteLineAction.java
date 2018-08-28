package com.test.app.action;

import com.test.app.dto.Quote;
import com.test.app.dto.QuoteLine;

import java.util.ArrayList;

public class AddQuoteLineAction extends QuoteAction{

    private QuoteLine quoteLine;

    public AddQuoteLineAction(Quote quote, ActionType actionType) {
        super(quote, actionType);
    }


    public void setQuoteLine(QuoteLine quoteLine) {
        this.quoteLine = quoteLine;
    }

    public void execute() {
        if(quote.getQuoteLines() == null) {
            quote.setQuoteLines(new ArrayList<QuoteLine>());
        }

        quote.getQuoteLines().add(quoteLine);
    }
}
