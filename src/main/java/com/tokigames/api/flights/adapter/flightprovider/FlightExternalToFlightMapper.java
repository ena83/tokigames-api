package com.tokigames.api.flights.adapter.flightprovider;

import com.tokigames.api.flights.adapter.flightprovider.model.FlightBusinessItemExternal;
import com.tokigames.api.flights.adapter.flightprovider.model.FlightCheapItemExternal;
import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.domain.FlightType;
import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class FlightExternalToFlightMapper {

    public List<Flight> toFlightsFromFlightCheapExternal(List<FlightCheapItemExternal> flightCheapItemsExternal) {
        return flightCheapItemsExternal
                .stream()
                .map(FlightExternalToFlightMapper::toFlightFromExternalCheap)
                .collect(Collectors.toList());
    }

    public List<Flight> toFlightsFromFlightBusinessExternal(List<FlightBusinessItemExternal> flightBusinessItemsExternal) {
        return flightBusinessItemsExternal
                .stream()
                .map(FlightExternalToFlightMapper::toFlightFromExternalBusiness)
                .collect(Collectors.toList());
    }

    private Flight toFlightFromExternalCheap(FlightCheapItemExternal flightCheapItemExternal) {
        return new Flight(flightCheapItemExternal.getRoute().split("-")[0],
                flightCheapItemExternal.getRoute().split("-")[1],
                toLocalDateTime(flightCheapItemExternal.getDeparture()),
                toLocalDateTime(flightCheapItemExternal.getArrival()),
                FlightType.CHEAP);
    }

    private Flight toFlightFromExternalBusiness(FlightBusinessItemExternal flightBusinessItemExternal) {
        return new Flight(flightBusinessItemExternal.getDeparture(),
                flightBusinessItemExternal.getArrival(),
                toLocalDateTime(flightBusinessItemExternal.getDepartureTime()),
                toLocalDateTime(flightBusinessItemExternal.getArrivalTime()),
                FlightType.BUSINESS);
    }

    private static LocalDateTime toLocalDateTime(long millis) {
        Instant instant = Instant.ofEpochMilli(millis);
        LocalDateTime date = instant.atZone(ZoneId.of("UTC")).toLocalDateTime();
        return date;
    }
}
