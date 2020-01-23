package com.tokigames.api.flights.application;

import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.infrastructure.FlightsRestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FlightsService {

    private FlightsRestRepository flightsRestRepository;

    public List<Flight> findFlights() {
        return flightsRestRepository.findAllFlights();
    }
}
