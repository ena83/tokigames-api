package com.tokigames.api.flights.adapter.web;

import com.tokigames.api.flights.application.FlightSearchService;
import com.tokigames.api.flights.domain.Flight;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public class FlightController {

    private FlightSearchService flightSearchService;

    @GetMapping(value = "/flights", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Flight> getFlights() {
        return flightSearchService.getFlights();
    }
}
