package com.tokigames.api.flights.adapter.flightprovider.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightBusinessItemExternal {
    private String departure;

    private String arrival;

    private long departureTime;

    private long arrivalTime;
}
