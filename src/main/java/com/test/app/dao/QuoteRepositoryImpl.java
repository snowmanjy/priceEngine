package com.test.app.dao;

import com.test.app.dto.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class QuoteRepositoryImpl implements BaseRepository<Quote> {

    @Autowired
    private RedisTemplate<String, Quote> quoteRedisTemplate;

    private ListOperations<String, Quote> listOperations;

    @PostConstruct
    private void init(){
        listOperations = quoteRedisTemplate.opsForList();
    }

    public void add(String symbol, Quote stockData) {
        listOperations.leftPush(symbol, stockData);
    }

    public void delete(String symbol) {
        listOperations.leftPop(symbol);
    }

    public Quote find(String symbol){
        return listOperations.index(symbol, 0);
    }

    public boolean hasKey(String symbol) {
        return listOperations.size(symbol) > 0;
    }

}
