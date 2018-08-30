package com.test.app.service;

import com.test.app.dao.QuoteDao;
import com.test.app.kafka.EventProducer;
import com.test.app.web.QuoteNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuoteServiceTest {

    @Mock
    private QuoteDao quoteDao;

    @Mock
    private EventProducer eventProducer;

    @InjectMocks
    private QuoteService quoteService = new QuoteService();

    @Test(expected = QuoteNotFoundException.class)
    public void testGetQuoteWithNullQuoteReturned() throws Exception {
        when(quoteDao.getQuote(any(String.class))).thenReturn(null);
        quoteService.getQuote("testQuote");
    }
}
