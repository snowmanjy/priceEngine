package com.test.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.app.action.QuoteActionFactory;
import com.test.app.dao.QuoteDao;
import com.test.app.dto.Quote;
import com.test.app.kafka.ActionProducer;
import com.test.app.web.QuoteNotFoundException;
import com.test.app.web.model.UpdateQuoteModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

	private static Logger logger = LoggerFactory.getLogger(QuoteService.class);

	@Autowired
	private QuoteDao quoteDao;

    @Autowired
    private ActionProducer actionProducer;

	public Quote getQuote(String quoteName) throws QuoteNotFoundException {

		Quote quote = quoteDao.getQuote(quoteName);

        if(quote == null) {
            throw new QuoteNotFoundException(quoteName);
        }

        return quote;
	}

    public void saveQuote(Quote quote) {

        quoteDao.createOrUpdateQuote(quote);

    }

    public void updateQuote(UpdateQuoteModel updateQuoteModel) throws QuoteNotFoundException {

        Quote quote = quoteDao.getQuote(updateQuoteModel.getQuoteName());

        if(quote == null) {
            throw new QuoteNotFoundException(updateQuoteModel.getQuoteName());
        }

        try {
            actionProducer.send(updateQuoteModel);
        } catch (JsonProcessingException e) {
            logger.error("Kafka pubisher error:" + e.getMessage());
        }

    }

}
