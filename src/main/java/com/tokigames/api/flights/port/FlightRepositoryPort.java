package com.tokigames.api.flights.port;

import com.tokigames.api.flights.domain.Flight;

import java.util.List;
import java.util.Set;

public interface FlightRepositoryPort {

    void saveFlights(List<Flight> flights);

    Set<Flight> getAllFlights();
}
