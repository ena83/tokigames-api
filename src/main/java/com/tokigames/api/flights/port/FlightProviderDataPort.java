package com.tokigames.api.flights.port;

import com.tokigames.api.flights.domain.Flight;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface FlightProviderDataPort {
    Mono<List<Flight>> getAllCheapFlights();

    Mono<List<Flight>> getAllBusinessFlights();

    Flux<Flight> getAllFlights();

}
