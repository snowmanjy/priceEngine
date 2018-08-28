package com.test.app.action;

import com.test.app.dto.Quote;
import com.test.app.dto.QuoteLine;

public class ChangeQuantityAction extends QuoteAction{

    private int lineNumber;

    private int newQuantity;

    public ChangeQuantityAction(Quote quote, ActionType actionType) {
        super(quote, actionType);
    }


    public void setQuantity(int quantity) {
        this.newQuantity = newQuantity;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void execute() {
        if(quote.getQuoteLines() != null && quote.getQuoteLines().size() > 0) {
            for(QuoteLine quoteLine : quote.getQuoteLines()) {
                if(lineNumber == quoteLine.getLineNumber()) {
                    quoteLine.setQuantity(newQuantity);
                }
            }
        }

    }
}
