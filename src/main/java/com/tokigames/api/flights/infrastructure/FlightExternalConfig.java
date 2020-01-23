package com.tokigames.api.flights.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class FlightExternalConfig {

    @Bean
    WebClient tokigamesFlightsWebClient() {
        return WebClient.create("https://tokigames-challenge.herokuapp.com");
    }
}
