package com.test.app.dao;

import com.test.app.dto.Quote;

import java.util.List;

public interface BaseRepository<T> {

    void add(String symbol, T value);

    void delete(String symbol);

    T find(String symbol);

    List<Quote> getAll(String symbol);

    boolean hasKey(String symbol);

}
