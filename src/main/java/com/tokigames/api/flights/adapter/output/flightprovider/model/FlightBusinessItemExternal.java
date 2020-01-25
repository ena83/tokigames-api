package com.tokigames.api.flights.adapter.output.flightprovider.model;

import lombok.Data;

@Data
public class FlightBusinessItemExternal {
    private String departure;

    private String arrival;

    private long departureTime;

    private long arrivalTime;
}
