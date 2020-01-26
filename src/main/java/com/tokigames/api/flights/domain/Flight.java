package com.tokigames.api.flights.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Flight {
    private String departure;

    private String arrival;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private FlightType type;

    public boolean isLandedInThisCity(String city) {
        return this.getDeparture().contains(city) || this.getArrival().contains(city);
    }
}
