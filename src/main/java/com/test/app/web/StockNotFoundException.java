package com.test.app.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not Found")
public class StockNotFoundException extends Exception {

    public StockNotFoundException(String stock) {
        super("Unknown stock: " + stock);
    }
}
