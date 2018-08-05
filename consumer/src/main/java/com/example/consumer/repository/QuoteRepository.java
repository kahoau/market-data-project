package com.example.consumer.repository;

import com.example.consumer.model.Quote;
import com.example.consumer.model.QuoteKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends CassandraRepository<Quote,QuoteKey> {

}