package com.tokigames.api.flights.port;

import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.domain.SortBy;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlightRepositoryPort {

    void saveFlights(List<Flight> flights);

    void deleteAllFlights();

    List<Flight> getFlights(String city, SortBy sortBy, Pageable page);
}
