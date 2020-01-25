package com.tokigames.api.flights.adapter.output.flightprovider;

import com.tokigames.api.flights.adapter.output.flightprovider.model.FlightBusinessExternal;
import com.tokigames.api.flights.adapter.output.flightprovider.model.FlightCheapExternal;
import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.port.FlightProviderDataPort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class FlightProviderDataRestAdapter implements FlightProviderDataPort {

    private final WebClient tokigamesFlightWebClient;

    public FlightProviderDataRestAdapter() {
        tokigamesFlightWebClient = WebClient.create("https://tokigames-challenge.herokuapp.com/api/flights");
    }

    @Override
    public Mono<List<Flight>> getAllCheapFlights() {
        return tokigamesFlightWebClient
                .get()
                .uri("/cheap")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(FlightCheapExternal.class)
                .map(flightCheapExternal -> Flight.toFlightsFromFlightCheapExternal(flightCheapExternal.getData()));
    }

    @Override
    public Mono<List<Flight>> getAllBusinessFlights() {
        return tokigamesFlightWebClient
                .get()
                .uri("/business")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(FlightBusinessExternal.class)
                .map(flightBusinessExternal -> Flight.toFlightsFromFlightBusinessExternal(flightBusinessExternal.getData()));
    }
}
