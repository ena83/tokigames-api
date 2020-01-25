package com.tokigames.api.flights.adapter.output.flightprovider.model;

import lombok.Data;

import java.util.List;

@Data
public class FlightCheapExternal {
    private List<FlightCheapItemExternal> data;
}
