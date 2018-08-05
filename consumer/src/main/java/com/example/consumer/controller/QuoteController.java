package com.example.consumer.controller;

import com.example.consumer.model.Quote;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class QuoteController {
    @RequestMapping(value = "/lastXPriceAvg/{numPrice}", method = RequestMethod.GET)
    public Quote getLastXPriceAvg(@PathVariable String numPrice) {
        return new Quote(11.7,new Date());
    }

//    Mono<Quote> getLastXPriceAvg(@PathVariable String numPrice) {
//
//    }

}
