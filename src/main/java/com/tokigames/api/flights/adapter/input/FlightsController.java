package com.tokigames.api.flights.adapter.input;

import com.tokigames.api.flights.application.FlightsService;
import com.tokigames.api.flights.domain.Flight;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FlightsController {

    private FlightsService flightsService;

    @GetMapping("/flights")
    public List<Flight> findFlights() {
        return flightsService.findFlights();
    }
}
