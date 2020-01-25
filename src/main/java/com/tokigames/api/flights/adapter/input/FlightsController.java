package com.tokigames.api.flights.adapter.input;

import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.port.FlightProviderDataPort;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
public class FlightsController {

    private FlightProviderDataPort flightProviderDataPort;

    @GetMapping(value = "/flights", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<List<Flight>> findFlights() {
        return flightProviderDataPort.getAllCheapFlights();
    }
}
