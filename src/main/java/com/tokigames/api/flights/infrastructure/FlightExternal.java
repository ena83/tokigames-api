package com.tokigames.api.flights.infrastructure;

import lombok.Data;

import java.util.List;

@Data
public class FlightExternal {
    private List<FlightExternalItem> data;
}
