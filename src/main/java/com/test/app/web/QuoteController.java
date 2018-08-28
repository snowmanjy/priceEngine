package com.test.app.web;

import com.test.app.dto.Quote;
import com.test.app.service.QuoteService;
import com.test.app.web.model.UpdateQuoteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/{quotename}")
    public Quote getQuote(@PathVariable String quotename) throws QuoteNotFoundException {
        if(quotename == null || quotename.trim().length() == 0) {
            throw new QuoteNotFoundException(quotename);
        }

        return quoteService.getQuote(quotename);
    }

    @PostMapping
    public ResponseEntity<?> addQuote(@RequestBody Quote quote) throws QuoteNotFoundException {
        if(quote == null) {
            throw new QuoteNotFoundException("");
        }

        quoteService.saveQuote(quote);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{quotename}")
                .buildAndExpand(quote.getName())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{quotename}")
    public ResponseEntity<?> updateQuote(@PathVariable("quotename") String quoteName, @RequestBody UpdateQuoteModel updateQuoteModel) throws QuoteNotFoundException {
        if(quoteName == null) {
            throw new QuoteNotFoundException("");
        }
        updateQuoteModel.setQuoteName(quoteName);

        quoteService.updateQuote(updateQuoteModel);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{quotename}")
                .buildAndExpand(quoteName)
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
