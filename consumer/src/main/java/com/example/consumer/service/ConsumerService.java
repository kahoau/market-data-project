package com.example.consumer.service;

import com.example.consumer.model.Quote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerService {
    private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @KafkaListener(topics = "${kafka.dataacquisition.topic}")
    public void receive(String message) throws IOException {
        log.info("received kafka message:" + message);

        ObjectMapper objectMapper = new ObjectMapper();
        Quote quote = objectMapper.readValue(message, Quote.class);

        save(quote);
    }

    // save to db
    private void save(Quote quote){

    }


}
