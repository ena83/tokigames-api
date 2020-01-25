package com.tokigames.api.flights.application;

import com.tokigames.api.flights.port.FlightProviderDataPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlightsDataUpdateService {

    private FlightProviderDataPort flightProviderDataPort;

    public void updateFlightData() {
        flightProviderDataPort
                .getAllCheapFlights()
                .subscribe(System.out::println);

        flightProviderDataPort
                .getAllBusinessFlights()
                .subscribe(flights -> System.out.println("BUSINESS"+flights));
    }
}
