package com.example.consumer.model;


import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.time.LocalDateTime;

@PrimaryKeyClass
public class QuoteKey implements Serializable {
    @PrimaryKeyColumn(
            name = "src_id",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED
    )
    private String srcId;

    @PrimaryKeyColumn(
            name = "ts",
            ordinal = 1,
            type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING)
    private LocalDateTime ts;

    public QuoteKey(String srcId, LocalDateTime ts) {
        this.srcId = srcId;
        this.ts = ts;
    }

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }

    public LocalDateTime getTs() {
        return ts;
    }

    public void setTs(LocalDateTime ts) {
        this.ts = ts;
    }
}