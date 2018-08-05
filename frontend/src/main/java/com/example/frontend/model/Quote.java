package com.example.frontend.model;

import java.math.BigDecimal;
import java.util.Date;

public class Quote {
        private String status;
        private BigDecimal price;

    public Quote() { }

    public Quote(String status, BigDecimal price) {
        this.status = status;
        this.price = price;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}