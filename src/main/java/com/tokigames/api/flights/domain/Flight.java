package com.tokigames.api.flights.domain;

import com.tokigames.api.flights.adapter.output.flightprovider.model.FlightBusinessItemExternal;
import com.tokigames.api.flights.adapter.output.flightprovider.model.FlightCheapItemExternal;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Flight {
    private String departure;

    private String arrival;

    private Date departureTime;

    private Date arrivalTime;

    public static List<Flight> toFlightsFromFlightCheapExternal(List<FlightCheapItemExternal> flightCheapItemsExternal) {
        return flightCheapItemsExternal
                .stream()
                .map(Flight::convertToFlightFromCheap)
                .collect(Collectors.toList());
    }

    public static List<Flight> toFlightsFromFlightBusinessExternal(List<FlightBusinessItemExternal> flightBusinessItemsExternal) {
        return flightBusinessItemsExternal
                .stream()
                .map(Flight::convertToFlightFromBusiness)
                .collect(Collectors.toList());
    }

    public static Flight convertToFlightFromCheap(FlightCheapItemExternal flightCheapItemExternal) {
        return new Flight(flightCheapItemExternal.getRoute().split("-")[0],
                flightCheapItemExternal.getRoute().split("-")[1],
                new Date(flightCheapItemExternal.getDeparture()),
                new Date(flightCheapItemExternal.getArrival()));
    }

    public static Flight convertToFlightFromBusiness(FlightBusinessItemExternal flightBusinessItemExternal) {
        return new Flight(flightBusinessItemExternal.getDeparture(),
                flightBusinessItemExternal.getArrival(),
                new Date(flightBusinessItemExternal.getDepartureTime()),
                new Date(flightBusinessItemExternal.getArrivalTime()));
    }
}
