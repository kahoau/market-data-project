package com.example.producer.service;

import com.example.producer.schedule.ProducerTask;
import com.example.producer.model.Quote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    private static final Logger log = LoggerFactory.getLogger(ProducerTask.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.dataacquisition.topic}")
    private String topic;

    public void send(Quote quote) throws JsonProcessingException {
        log.info("The price is now {}",quote.getPrice());

        //Object to JSON in String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(quote);

        kafkaTemplate.send(topic, jsonInString);

        log.info("Sent to kafka "+ topic + " topic");
    }
}
