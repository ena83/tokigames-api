package com.tokigames.api.flights.adapter.output.persistence;

import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.port.FlightRepositoryPort;

import java.util.List;

public class FlightInMemoryRepository implements FlightRepositoryPort {
    @Override
    public void saveFlightToCache(List<Flight> flights) {

    }
}
