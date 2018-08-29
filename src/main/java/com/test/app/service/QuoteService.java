package com.test.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.app.dao.QuoteDao;
import com.test.app.dto.Quote;
import com.test.app.kafka.ActionProducer;
import com.test.app.web.QuoteNotFoundException;
import com.test.app.dto.QuoteActionModel;
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

    public void updateQuote(QuoteActionModel quoteActionModel) throws QuoteNotFoundException {

        Quote quote = quoteDao.getQuote(quoteActionModel.getQuoteName());

        if(quote == null) {
            throw new QuoteNotFoundException(quoteActionModel.getQuoteName());
        }

        try {
            actionProducer.send(quoteActionModel);
        } catch (JsonProcessingException e) {
            logger.error("Kafka pubisher error:" + e.getMessage());
        }

    }

}
