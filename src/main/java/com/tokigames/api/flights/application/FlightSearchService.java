package com.tokigames.api.flights.application;

import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.port.FlightRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class FlightSearchService {

    private FlightRepositoryPort flightRepositoryPort;

    public Flux<Flight> getFlights() {
        return Flux.fromIterable(flightRepositoryPort.getAllFlights());
    }
}
