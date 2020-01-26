package com.tokigames.api.flights.adapter.flightprovider.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightCheapItemExternal {
    private String route;

    private long departure;

    private long arrival;
}
