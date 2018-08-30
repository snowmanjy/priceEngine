package com.test.app.action;

import com.test.app.dto.Quote;
import com.test.app.dto.QuoteActionModel;
import org.springframework.stereotype.Component;

@Component
public class QuoteActionFactory {

    public QuoteActionHandler getQuoteAction(Quote quote, QuoteActionModel quoteActionModel) {
        switch (quoteActionModel.getActionType()) {
            case ADDQUOTELINE:
                AddQuoteLineActionHandler addQuoteLineActionHandler = new AddQuoteLineActionHandler(quote);
                addQuoteLineActionHandler.setQuoteLine(quoteActionModel.getQuoteLine());
                return addQuoteLineActionHandler;
            case CHANGEDISCOUNT:
                ChangeDiscountActionHandler changeDiscountActionHandler = new ChangeDiscountActionHandler(quote);
                changeDiscountActionHandler.setDiscount(quoteActionModel.getDiscount());
                return changeDiscountActionHandler;
            case CHANGEQUATITY:
                ChangeQuantityActionHandler changeQuantityActionHandler = new ChangeQuantityActionHandler(quote);
                changeQuantityActionHandler.setLineNumber(quoteActionModel.getQuoteLine().getLineNumber());
                changeQuantityActionHandler.setQuantity(quoteActionModel.getQuoteLine().getQuantity());
                return changeQuantityActionHandler;
            case CHANGESHIPPINGFEE:
                ChangeShippingFeeActionHandler changeShippingFeeActionHandler = new ChangeShippingFeeActionHandler(quote);
                changeShippingFeeActionHandler.setNewShippingFee(quoteActionModel.getShippingFee());
                return changeShippingFeeActionHandler;
            case CHANGECOUPON:
                ChangeCouponActionHandler changeCouponActionHandler = new ChangeCouponActionHandler(quote);
                changeCouponActionHandler.setCoupon(quoteActionModel.getCoupon());
                return changeCouponActionHandler;
            case CREATEQUOTE:
                CreateQuoteActionHandler createQuoteActionHandler = new CreateQuoteActionHandler(quote);
                return createQuoteActionHandler;
            default:
                return null;
        }
    }
}
