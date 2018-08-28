package com.test.app.action;

import com.test.app.dto.Quote;
import com.test.app.web.model.UpdateQuoteModel;
import org.springframework.stereotype.Component;

@Component
public class QuoteActionFactory {

    public QuoteAction getQuoteAction(Quote quote, UpdateQuoteModel updateQuoteModel) {
        switch (updateQuoteModel.getActionType()) {
            case ADDQUOTELINE:
                AddQuoteLineAction addQuoteLineAction = new AddQuoteLineAction(quote, updateQuoteModel.getActionType());
                addQuoteLineAction.setQuoteLine(updateQuoteModel.getQuoteLine());
                return addQuoteLineAction;
            case CHANGEDISCOUNT:
                ChangeDiscountAction changeDiscountAction = new ChangeDiscountAction(quote, updateQuoteModel.getActionType());
                changeDiscountAction.setDiscount(updateQuoteModel.getDiscount());
                return changeDiscountAction;
            case CHANGEQUATITY:
                ChangeQuantityAction changeQuantityAction = new ChangeQuantityAction(quote, updateQuoteModel.getActionType());
                changeQuantityAction.setLineNumber(updateQuoteModel.getQuoteLine().getLineNumber());
                changeQuantityAction.setQuantity(updateQuoteModel.getQuoteLine().getQuantity());
                return changeQuantityAction;

            default:
                return null;
        }
    }
}
