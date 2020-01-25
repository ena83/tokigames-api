package com.tokigames.api.flights.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ApplicationScheduler {

    @Autowired
    private FlightsDataUpdateService flightsDataUpdateService;

    @Scheduled(fixedDelay = 10000L)
    public void updateAllFlights() {
        flightsDataUpdateService.updateFlightData();
    }
}
