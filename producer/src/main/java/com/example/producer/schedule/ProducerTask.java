package com.example.producer.schedule;

import com.example.producer.model.Quote;
import com.example.producer.service.ProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class ProducerTask {
    private static final Logger log = LoggerFactory.getLogger(ProducerTask.class);

    @Autowired
    private ProducerService producerService;

    private final Double rangeMin = 0.0;
    private final Double rangeMax = 100.0;

    @Scheduled(cron = "${mock.data.generation.rate}")
    public void generateQuote() {
        Random r = new Random();
        double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        Quote quote = new Quote(randomValue,new Date());
        try{
            producerService.send(quote);
        } catch (JsonProcessingException je){
            log.error("Error processing quote to json to send to kafka ");
        }
    }
}
