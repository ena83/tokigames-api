package com.tokigames.api.flights.application;

import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.domain.SortBy;
import com.tokigames.api.flights.port.FlightRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class FlightSearchService {

    private final FlightRepositoryPort flightRepositoryPort;

    public Flux<Flight> getFlights(String city, SortBy sortBy, Pageable page) {
        return Flux.fromIterable(flightRepositoryPort.getFlights(city, sortBy, page));
    }
}
