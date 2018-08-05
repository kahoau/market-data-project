package com.example.consumer.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table("quote")
public class Quote {
    @PrimaryKey
    private QuoteKey key;

    @Column("price")
    private BigDecimal price;

    public Quote(QuoteKey key, BigDecimal price) {
        this.key = key;
        this.price = price;
    }

    public QuoteKey getKey() {
        return key;
    }

    public void setKey(QuoteKey key) {
        this.key = key;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static Quote of(QuoteProducer quoteProducer){
        return new Quote(new QuoteKey(quoteProducer.getSrcId(), LocalDateTime.now()), quoteProducer.getPrice());
    }
}

/*
https://dzone.com/articles/getting-started-with-spring-data-cassandra
https://stackoverflow.com/questions/44448899/spring-data-for-apache-cassandra-converts-java-time-localdatetime-to-utc
        */