package com.example.consumer.model;

import java.math.BigDecimal;

public class QuoteProducer {
    private String srcId;
    private BigDecimal price;

    public QuoteProducer() {
    }

    public QuoteProducer(String srcId, BigDecimal price) {
        this.srcId = srcId;
        this.price = price;
    }

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
