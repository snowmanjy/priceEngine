package com.test.app.service;

import com.test.app.dao.QuoteDao;
import com.test.app.dto.Quote;
import com.test.app.kafka.EventProducer;
import com.test.app.kafka.SampleMessage;
import com.test.app.web.QuoteNotFoundException;
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
    private EventProducer eventProducer;

	public Quote getQuote(String quoteName) throws QuoteNotFoundException {

		Quote quote = quoteDao.getQuote(quoteName);

        if(quote == null) {
            throw new QuoteNotFoundException(quoteName);
        }

        return quote;
	}

    public void saveQuote(Quote quote) {

        quoteDao.createOrUpdateQuote(quote);

        //TODO data change
        eventProducer.send(new SampleMessage(1, "A simple test message"));

    }

}
