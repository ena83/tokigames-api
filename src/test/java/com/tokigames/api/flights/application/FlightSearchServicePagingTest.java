package com.tokigames.api.flights.application;

import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.domain.FlightType;
import com.tokigames.api.flights.domain.SortBy;
import com.tokigames.api.flights.port.FlightProviderDataPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightSearchServicePagingTest {

    @Mock
    private FlightProviderDataPort flightProviderDataPort;

    @InjectMocks
    private FlightSearchService flightSearchService;

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
        when(flightProviderDataPort.getAllFlights()).thenReturn(Flux.fromIterable(Sets.newSet(flight1, flight2, flight3)));
    }

    @Test
    void shouldOnlyReturnListAccordingToPage() {
        //when
        List<Flight> flightFoundPage1 = Flux.concat(flightSearchService.getFlights("",
                SortBy.DEPARTURE_CITY,
                PageRequest.of(0, 2))).collectList().block();
        List<Flight> flightFoundPage2 = Flux.concat(flightSearchService.getFlights("",
                SortBy.DEPARTURE_CITY,
                PageRequest.of(1, 2))).collectList().block();

        //then
        assertThat(flightFoundPage1.size(), is(2));
        assertThat(flightFoundPage2.size(), is(1));
    }
}