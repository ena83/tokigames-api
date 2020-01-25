package com.tokigames.api.flights.adapter.output.flightprovider.model;

import lombok.Data;

@Data
public class FlightCheapItemExternal {
    private String route;

    private long departure;

    private long arrival;
}
