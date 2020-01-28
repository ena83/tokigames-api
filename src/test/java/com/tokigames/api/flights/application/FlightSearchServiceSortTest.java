package com.tokigames.api.flights.application;

import com.tokigames.api.flights.adapter.persistence.FlightInMemoryRepository;
import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.domain.FlightType;
import com.tokigames.api.flights.domain.SortBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class FlightSearchServiceSortTest {

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
                "Singapore",
                LocalDateTime.of(2020, 01, 20, 1, 00),
                LocalDateTime.of(2020, 01, 20, 3, 00),
                FlightType.CHEAP);
        Flight flight3 = new Flight("Antalya",
                "Paris",
                LocalDateTime.of(2020, 01, 12, 1, 00),
                LocalDateTime.of(2020, 01, 12, 3, 00),
                FlightType.CHEAP);
        Flight flight4 = new Flight("Paris",
                "Singapore",
                LocalDateTime.of(2020, 01, 22, 1, 00),
                LocalDateTime.of(2020, 01, 22, 3, 00),
                FlightType.CHEAP);
        flightInMemoryRepository.deleteAllFlights();
        flightInMemoryRepository.saveFlights(List.of(flight1, flight2, flight3, flight4));
    }

    @Test
    void shouldSortFlightListByDepartureCity() {
        //when
        List<Flight> flightFound = Flux.concat(flightSearchService.getFlights("",
                SortBy.DEPARTURE_CITY,
                PageRequest.of(0, 20))).collectList().block();

        //then
        assertThat(flightFound.size(), is(4));

        Flight flight1 = flightFound.get(0);
        Flight flight2 = flightFound.get(1);
        Flight flight3 = flightFound.get(2);
        Flight flight4 = flightFound.get(3);

        assertThat(flight1.getDeparture(), is("Antalya"));
        assertThat(flight2.getDeparture(), is("Paris"));
        assertThat(flight3.getDeparture(), is("Paris"));
        assertThat(flight4.getDeparture(), is("Singapore"));
    }

    @Test
    void shouldSortFlightListByArrivalCity() {
        //when
        List<Flight> flightFound = Flux.concat(flightSearchService.getFlights("",
                SortBy.ARRIVAL_CITY,
                PageRequest.of(0, 20))).collectList().block();

        //then
        assertThat(flightFound.size(), is(4));

        Flight flight1 = flightFound.get(0);
        Flight flight2 = flightFound.get(1);
        Flight flight3 = flightFound.get(2);
        Flight flight4 = flightFound.get(3);

        assertThat(flight1.getArrival(), is("Istanbul"));
        assertThat(flight2.getArrival(), is("Paris"));
        assertThat(flight3.getArrival(), is("Singapore"));
        assertThat(flight4.getArrival(), is("Singapore"));
    }

    @Test
    void shouldSortFlightListByDepartureAndTimeCity() {
        //when
        List<Flight> flightFound = Flux.concat(flightSearchService.getFlights("",
                SortBy.DEPARTURE_CITY_AND_TIME,
                PageRequest.of(0, 20))).collectList().block();

        //then
        assertThat(flightFound.size(), is(4));

        Flight flight1 = flightFound.get(0);
        Flight flight2 = flightFound.get(1);
        Flight flight3 = flightFound.get(2);
        Flight flight4 = flightFound.get(3);

        assertThat(flight1.getDeparture(), is("Antalya"));
        assertThat(flight2.getDeparture(), is("Paris"));
        assertThat(flight3.getDeparture(), is("Paris"));
        assertThat(flight4.getDeparture(), is("Singapore"));

    }
}