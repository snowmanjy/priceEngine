package com.test.app.action;

import com.test.app.dto.Quote;
import com.test.app.dto.QuoteLine;

public class ChangeQuantityActionHandler extends QuoteActionHandler {

    private int lineNumber;

    private int newQuantity;

    public ChangeQuantityActionHandler(Quote quote) {
        super(quote);
    }

    public void setQuantity(int quantity) {
        this.newQuantity = quantity;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Quote handle() {
        if(quote.getQuoteLines() != null && quote.getQuoteLines().size() > 0) {
            for(QuoteLine quoteLine : quote.getQuoteLines()) {
                if(lineNumber == quoteLine.getLineNumber()) {
                    quoteLine.setQuantity(newQuantity);
                }
            }
        }
        return quote;
    }
}
