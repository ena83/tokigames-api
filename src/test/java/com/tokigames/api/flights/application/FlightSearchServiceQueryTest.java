package com.tokigames.api.flights.application;

import com.tokigames.api.flights.adapter.persistence.FlightInMemoryRepository;
import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.domain.FlightType;
import com.tokigames.api.flights.domain.SortBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
class FlightSearchServiceQueryTest {

    private FlightInMemoryRepository flightInMemoryRepository = new FlightInMemoryRepository();

    private FlightSearchService flightSearchService = new FlightSearchService(flightInMemoryRepository);

    @BeforeEach
    public void setup() {
        //given
        Flight flight1 = new Flight("Singapore",
                "Istanbul",
                LocalDateTime.of(2020, 01, 20, 1, 50),
                LocalDateTime.of(2020, 01, 21, 8, 00),
                FlightType.BUSINESS);
        Flight flight2 = new Flight("Paris",
                "Istanbul",
                LocalDateTime.of(2020, 01, 20, 1, 00),
                LocalDateTime.of(2020, 01, 20, 3, 00),
                FlightType.CHEAP);
        Flight flight3 = new Flight("Paris",
                "Istanbul",
                LocalDateTime.of(2020, 01, 12, 1, 00),
                LocalDateTime.of(2020, 01, 12, 3, 00),
                FlightType.CHEAP);
        flightInMemoryRepository.deleteAllFlights();
        flightInMemoryRepository.saveFlights(List.of(flight1, flight2, flight3));
    }

    @Test
    void shouldReturnOnlyFlightsThatAreLandedInSingapore() {
        //when
        List<Flight> flightFound = Flux.concat(flightSearchService.getFlights("Singapore",
                SortBy.DEPARTURE_CITY,
                PageRequest.of(0, 20))).collectList().block();

        //then
        assertThat(flightFound.size(), is(1));
    }

    @Test
    void shouldReturnOnlyFlightsThatAreLandedInParis() {
        //when
        List<Flight> flightFound = Flux.concat(flightSearchService.getFlights("Paris",
                SortBy.DEPARTURE_CITY,
                PageRequest.of(0, 20))).collectList().block();

        //then
        assertThat(flightFound.size(), is(2));
    }
}