package com.tokigames.api.flights.adapter.persistence;

import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.port.FlightRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Repository
public class FlightInMemoryRepository implements FlightRepositoryPort {

    private static Set<Flight> FLIGHTS_IN_MEMORY = new HashSet<>();

    @Override
    public void saveFlights(List<Flight> flights) {
        FLIGHTS_IN_MEMORY.addAll(flights);
    }

    @Override
    public Set<Flight> getAllFlights() {
        return FLIGHTS_IN_MEMORY;
    }
}
