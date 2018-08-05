package com.example.consumer.service;

import com.example.consumer.model.Quote;
import com.example.consumer.model.QuoteProducer;
import com.example.consumer.model.QuoteResult;
import com.example.consumer.repository.QuoteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ConsumerService {
    private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private CassandraTemplate cassandraTemplate;


    @KafkaListener(topics = "${kafka.dataacquisition.topic}")
    public void receive(String message) throws IOException {
        log.info("received kafka message:" + message);

        ObjectMapper objectMapper = new ObjectMapper();
        QuoteProducer quoteProducer = objectMapper.readValue(message, QuoteProducer.class);

        quoteRepository.save(Quote.of(quoteProducer));
    }



    public QuoteResult getLastXPrice(int numPrice){
        List<Quote> lastXQuotes = cassandraTemplate.select("select * from quote limit "+numPrice,Quote.class);

        BigDecimal sumQuote = lastXQuotes.stream().map( x -> x.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal avgQuote = sumQuote.divide(new BigDecimal(lastXQuotes.size()));

        return new QuoteResult("success",avgQuote);
    }
}
