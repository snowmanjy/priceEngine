package com.test.app.web;

import com.test.app.dto.Response;
import com.test.app.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/getMaxProfitDates")
    @ResponseBody
    public Response getMaxProfitDates(@RequestParam(value="stock") String stock) throws StockNotFoundException {
        if(stock == null || stock.trim().length() == 0) {
            throw new StockNotFoundException(stock);
        }
        return stockService.getMaxProfitDates(stock);
    }
}
