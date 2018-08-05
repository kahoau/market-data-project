package com.example.frontend.model;

import java.util.Date;

public class Quote {
    private Double price;
    private Date timestamp;

    public Quote() {}
    public Quote(Double price, Date timestamp) {
        this.price = price;
        this.timestamp = timestamp;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
