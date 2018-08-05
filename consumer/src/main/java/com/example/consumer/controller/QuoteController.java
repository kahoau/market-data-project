package com.example.consumer.controller;

import com.example.consumer.model.Quote;
import com.example.consumer.model.QuoteKey;
import com.example.consumer.model.QuoteResult;
import com.example.consumer.service.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class QuoteController {
    private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    private ConsumerService consumerService;

    @RequestMapping(value = "/lastXPriceAvg/{numPrice}", method = RequestMethod.GET)
    public QuoteResult getLastXPriceAvg(@PathVariable int numPrice) {
        QuoteResult quoteResult = consumerService.getLastXPrice(numPrice);
        log.info("The avg price is "+quoteResult.getPrice());

        return quoteResult;
    }

//    Mono<Quote> getLastXPriceAvg(@PathVariable String numPrice) {
//
//    }

}
