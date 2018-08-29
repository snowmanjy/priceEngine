package com.test.app.action;

import com.test.app.dto.Quote;
import com.test.app.dto.QuoteActionModel;
import org.springframework.stereotype.Component;

@Component
public class QuoteActionFactory {

    public QuoteAction getQuoteAction(Quote quote, QuoteActionModel quoteActionModel) {
        switch (quoteActionModel.getActionType()) {
            case ADDQUOTELINE:
                AddQuoteLineAction addQuoteLineAction = new AddQuoteLineAction(quote, quoteActionModel.getActionType());
                addQuoteLineAction.setQuoteLine(quoteActionModel.getQuoteLine());
                return addQuoteLineAction;
            case CHANGEDISCOUNT:
                ChangeDiscountAction changeDiscountAction = new ChangeDiscountAction(quote, quoteActionModel.getActionType());
                changeDiscountAction.setDiscount(quoteActionModel.getDiscount());
                return changeDiscountAction;
            case CHANGEQUATITY:
                ChangeQuantityAction changeQuantityAction = new ChangeQuantityAction(quote, quoteActionModel.getActionType());
                changeQuantityAction.setLineNumber(quoteActionModel.getQuoteLine().getLineNumber());
                changeQuantityAction.setQuantity(quoteActionModel.getQuoteLine().getQuantity());
                return changeQuantityAction;

            default:
                return null;
        }
    }
}
