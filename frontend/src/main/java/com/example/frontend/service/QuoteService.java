package com.example.frontend.service;

import com.example.frontend.model.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class QuoteService {
    private static final Logger log = LoggerFactory.getLogger(QuoteService.class);

    private WebClient webClient = WebClient.create();

    @Value("${consumer.base.url}")
    private String consumerBaseUrl;

    /**
     *
     * @param numPrice
     * @return Quote which consists of avgPrice and timestamp
     *
     */
    public Quote getLastXPriceAvg(int numPrice) {
        Mono<ClientResponse> result = webClient.get()
                .uri(consumerBaseUrl+"/api/lastXPriceAvg/" + numPrice)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();

        Quote quote =  result.flatMap(res -> res.bodyToMono(Quote.class)).block();
        log.info("quote from consumer: "+ quote.getPrice());

        return quote;
    }
}
