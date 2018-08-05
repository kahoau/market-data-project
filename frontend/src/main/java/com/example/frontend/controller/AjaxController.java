package com.example.frontend.controller;

import com.example.frontend.model.Quote;
import com.example.frontend.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AjaxController {
    private static final Logger log = LoggerFactory.getLogger(AjaxController.class);

    @Autowired
    private QuoteService quoteService;

    @RequestMapping(value= "/api/lastXPriceAvg/{lastXPrice}", method= RequestMethod.GET)
    public Quote getLastXPriceAvg(@PathVariable("lastXPrice") int lastXPrice){
        log.info("lastXPrice: "+ lastXPrice);

        Quote quote = quoteService.getLastXPriceAvg(lastXPrice);
        return quote;
    }
}
