package com.tokigames.api.flights.port;

import com.tokigames.api.flights.domain.Flight;

import java.util.List;

public interface FlightRepositoryPort {

    void saveFlightToCache(List<Flight> flights);
}
