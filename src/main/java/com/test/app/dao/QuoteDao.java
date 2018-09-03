package com.test.app.dao;

import com.test.app.dto.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuoteDao {

    private static Logger logger = LoggerFactory.getLogger(QuoteDao.class);

    @Autowired
    private BaseRepository<Quote> quoteRepository;

    public Quote getQuote(String quoteName) {
        return quoteRepository.find(quoteName);
    }

    public void createOrUpdateQuote(Quote quote) {
        quoteRepository.add(quote.getName(), quote);
    }

    public List<Quote> getAllQuotes(String quoteName) {
        return quoteRepository.getAll(quoteName);
    }
}
