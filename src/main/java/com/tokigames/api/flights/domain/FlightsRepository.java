package com.tokigames.api.flights.domain;

import java.util.List;

public interface FlightsRepository {

    List<Flight> findAllFlights();
}
