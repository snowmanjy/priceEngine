package com.test.app.dao;

public interface BaseRepository<T> {

    void add(String symbol, T value);

    void delete(String symbol);

    T find(String symbol);

    boolean hasKey(String symbol);

}
