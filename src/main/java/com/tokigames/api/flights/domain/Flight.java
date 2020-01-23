package com.tokigames.api.flights.domain;

import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Data
public class Flight {
    private String departure;

    private String arrival;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;
}
