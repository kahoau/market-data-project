package com.example.consumer;

import com.example.consumer.model.Quote;
import com.example.consumer.model.QuoteKey;
import com.example.consumer.repository.QuoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConsumerApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(ConsumerApplicationTests.class);

	// Spring Boot will create a `WebTestClient` for you,
	// already configure and ready to issue requests against "localhost:RANDOM_PORT"
	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private QuoteRepository quoteRepository;

	@Autowired
	private CassandraTemplate cassandraTemplate;
	@Test
	public void testHello() {
		String str = webTestClient
				// Create a GET request to test an endpoint
				.get().uri("/api/lastXPriceAvg/"+1)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				// and use the dedicated DSL to test assertions against the response
				.expectStatus().isOk()
				.expectBody(String.class).returnResult().getResponseBody();

		log.info("the string is: "+ str);
	}

	@Test
	public void testFindAllQuotes() {
		List<Quote> quotes = quoteRepository.findAll();
		Quote firstQuote = quotes.get(0);

		log.info("testCasRepo: "+ firstQuote.getPrice()+ " "+firstQuote.getKey().getSrcId()+ " "+ firstQuote.getKey().getTs() );
	}

	@Test
	public void testFindXQuotes() {
		List<Quote> lastXQuotes = cassandraTemplate.select("select * from quote limit 5",Quote.class);
		Quote firstXQuote = lastXQuotes.get(0);

		log.info("testCasRepo: "+ firstXQuote.getPrice()+ " "+firstXQuote.getKey().getSrcId()+ " "+ firstXQuote.getKey().getTs() );
	}

	@Test
	public void testSaveQuote(){
		BigDecimal price = new BigDecimal(1110.288);
		price = price.setScale(2, RoundingMode.CEILING);
		Quote quote = new Quote(new QuoteKey("99", LocalDateTime.now()), price);

		quoteRepository.save(quote);
	}
}
