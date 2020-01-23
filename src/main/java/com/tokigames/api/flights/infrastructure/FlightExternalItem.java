package com.tokigames.api.flights.infrastructure;

import lombok.Data;

@Data
public class FlightExternalItem {
    private String departure;

    private String arrival;

    private long departureTime;

    private long arrivalTime;
}
