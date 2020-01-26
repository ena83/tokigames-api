package com.tokigames.api.flights.application;

import com.tokigames.api.flights.port.FlightProviderDataPort;
import com.tokigames.api.flights.port.FlightRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class FlightDataUpdateService {

    private FlightProviderDataPort flightProviderDataPort;

    private FlightRepositoryPort flightRepositoryPort;

    public void updateFlightData() {

        log.info("Updating all flights data");
        flightProviderDataPort
                .getAllCheapFlights()
                .subscribe(flights->flightRepositoryPort.saveFlights(flights));

        flightProviderDataPort
                .getAllBusinessFlights()
                .subscribe(flights->flightRepositoryPort.saveFlights(flights));
    }
}
