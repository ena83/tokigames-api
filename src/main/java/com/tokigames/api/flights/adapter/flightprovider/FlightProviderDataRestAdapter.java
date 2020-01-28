package com.tokigames.api.flights.adapter.flightprovider;

import com.tokigames.api.flights.adapter.flightprovider.model.FlightBusinessExternal;
import com.tokigames.api.flights.adapter.flightprovider.model.FlightCheapExternal;
import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.port.FlightProviderDataPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class FlightProviderDataRestAdapter implements FlightProviderDataPort {

    private final WebClient tokigamesFlightWebClient;

    public FlightProviderDataRestAdapter(@Value("${tokigames.flightprovider.api.url}")
                                                 String tokigamesUrl) {
        tokigamesFlightWebClient = WebClient.create(tokigamesUrl);
    }

    @Override
    public Mono<List<Flight>> getAllCheapFlights() {
        return tokigamesFlightWebClient
                .get()
                .uri("/cheap")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(FlightCheapExternal.class)
                .map(flightCheapExternal -> FlightExternalToFlightMapper.toFlightsFromFlightCheapExternal(flightCheapExternal.getData()));
    }

    @Override
    public Mono<List<Flight>> getAllBusinessFlights() {
        return tokigamesFlightWebClient
                .get()
                .uri("/business")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(FlightBusinessExternal.class)
                .map(flightBusinessExternal -> FlightExternalToFlightMapper.toFlightsFromFlightBusinessExternal(flightBusinessExternal.getData()));
    }

    @Override
    public Flux<Flight> getAllFlights() {
        return getAllCheapFlights().flatMapMany(Flux::fromIterable)
                .mergeWith(getAllBusinessFlights().flatMapMany(Flux::fromIterable));
    }
}
