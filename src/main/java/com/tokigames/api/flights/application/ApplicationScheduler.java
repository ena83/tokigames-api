package com.tokigames.api.flights.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ApplicationScheduler {

    @Autowired
    private FlightDataUpdateService flightDataUpdateService;

    @Scheduled(fixedDelay = 30000L)
    public void updateAllFlights() {
        flightDataUpdateService.updateFlightData();
    }
}
