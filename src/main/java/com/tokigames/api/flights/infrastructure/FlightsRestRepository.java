package com.tokigames.api.flights.infrastructure;

import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.domain.FlightsRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@AllArgsConstructor
public class FlightsRestRepository implements FlightsRepository {

    private WebClient tokigamesFlightWebClient;

    @Override
    public List<Flight> findAllFlights() {

        Mono<FlightExternal> cheapFlights = tokigamesFlightWebClient
                .get()
                .uri("/api/flights/cheap")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(FlightExternal.class);

        cheapFlights.subscribe(System.out::println);

        Mono<FlightExternal> businessFlights = tokigamesFlightWebClient
                .get()
                .uri("/api/flights/business")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(FlightExternal.class);

        businessFlights.subscribe(System.out::println);


        return null;
    }
}
