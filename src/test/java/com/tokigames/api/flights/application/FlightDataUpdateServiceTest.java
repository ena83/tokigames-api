package com.tokigames.api.flights.application;

import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.domain.FlightType;
import com.tokigames.api.flights.port.FlightProviderDataPort;
import com.tokigames.api.flights.port.FlightRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FlightDataUpdateServiceTest {

    @Mock
    private FlightProviderDataPort flightProviderDataPort;

    @Mock
    private FlightRepositoryPort flightRepositoryPort;

    @InjectMocks
    private FlightDataUpdateService flightDataUpdateService;

    @Test
    void shouldAggregateAndSaveAllFlightDataFromProviders() {
        //given
        List<Flight> cheapFlights = getCheapFlights();
        List<Flight> businessFlights = getBusinessFlights();

        when(flightProviderDataPort.getAllCheapFlights()).thenReturn(Mono.justOrEmpty(cheapFlights));
        when(flightProviderDataPort.getAllBusinessFlights()).thenReturn(Mono.justOrEmpty(businessFlights));

        //when
        flightDataUpdateService.updateFlightData();

        //then
        verify(flightRepositoryPort, times(1)).saveFlights(cheapFlights);
        verify(flightRepositoryPort, times(1)).saveFlights(businessFlights);

    }

    private List<Flight> getBusinessFlights() {
        Flight businessFlight1 = new Flight("Singapore",
                "Istanbul",
                LocalDateTime.of(2020, 01, 23, 1, 50),
                LocalDateTime.of(2020, 01, 24, 8, 00),
                FlightType.BUSINESS);
        Flight businessFlight2 = new Flight("Paris",
                "Singapore",
                LocalDateTime.of(2020, 01, 25, 1, 00),
                LocalDateTime.of(2020, 01, 25, 3, 00),
                FlightType.BUSINESS);
        Flight businessFlight3 = new Flight("Antalya",
                "Paris",
                LocalDateTime.of(2020, 01, 27, 1, 00),
                LocalDateTime.of(2020, 01, 27, 3, 00),
                FlightType.BUSINESS);
        return List.of(businessFlight1, businessFlight2, businessFlight3);
    }

    private List<Flight> getCheapFlights() {
        Flight cheapFlight1 = new Flight("Singapore",
                "Istanbul",
                LocalDateTime.of(2020, 01, 20, 1, 50),
                LocalDateTime.of(2020, 01, 21, 8, 00),
                FlightType.CHEAP);
        Flight cheapFlight2 = new Flight("Paris",
                "Singapore",
                LocalDateTime.of(2020, 01, 20, 1, 00),
                LocalDateTime.of(2020, 01, 20, 3, 00),
                FlightType.CHEAP);
        Flight cheapFlight3 = new Flight("Antalya",
                "Paris",
                LocalDateTime.of(2020, 01, 12, 1, 00),
                LocalDateTime.of(2020, 01, 12, 3, 00),
                FlightType.CHEAP);
        return List.of(cheapFlight1, cheapFlight2, cheapFlight3);
    }
}