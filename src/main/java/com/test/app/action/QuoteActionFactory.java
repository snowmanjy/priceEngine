package com.test.app.action;

import com.test.app.dto.Quote;
import com.test.app.dto.QuoteActionModel;
import org.springframework.stereotype.Component;

@Component
public class QuoteActionFactory {

    public QuoteActionHandler getQuoteAction(Quote quote, QuoteActionModel quoteActionModel) {
        switch (quoteActionModel.getActionType()) {
            case ADDQUOTELINE:
                AddQuoteLineActionHandler addQuoteLineAction = new AddQuoteLineActionHandler(quote);
                addQuoteLineAction.setQuoteLine(quoteActionModel.getQuoteLine());
                return addQuoteLineAction;
            case CHANGEDISCOUNT:
                ChangeDiscountActionHandler changeDiscountAction = new ChangeDiscountActionHandler(quote);
                changeDiscountAction.setDiscount(quoteActionModel.getDiscount());
                return changeDiscountAction;
            case CHANGEQUATITY:
                ChangeQuantityActionHandler changeQuantityAction = new ChangeQuantityActionHandler(quote);
                changeQuantityAction.setLineNumber(quoteActionModel.getQuoteLine().getLineNumber());
                changeQuantityAction.setQuantity(quoteActionModel.getQuoteLine().getQuantity());
                return changeQuantityAction;

            default:
                return null;
        }
    }
}
