package com.test.app.dao;

import com.test.app.dto.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteDao {

    private static Logger logger = LoggerFactory.getLogger(QuoteDao.class);

    @Autowired
    private BaseRepository<Quote> quoteRepository;

    public Quote getQuote(String quoteName) {
        Quote quote = quoteRepository.find(quoteName);

        if(quote != null) {
            logger.info("Quote in redis: " + quote.toString());
        }

        return quote;
    }

    public void createOrUpdateQuote(Quote quote) {
        if(!quoteRepository.hasKey(quote.getName())) {
            quoteRepository.add(quote.getName(), quote);
        } else {
            //TODO update Quote instead of delete and add
            quoteRepository.delete(quote.getName());
            quoteRepository.add(quote.getName(), quote);
        }
    }
}
