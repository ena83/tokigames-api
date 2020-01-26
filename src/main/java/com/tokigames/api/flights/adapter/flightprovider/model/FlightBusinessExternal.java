package com.tokigames.api.flights.adapter.flightprovider.model;

import lombok.Data;

import java.util.List;

@Data
public class FlightBusinessExternal {
    private List<FlightBusinessItemExternal> data;
}
