package com.tokigames.api.flights.application;

import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.domain.SortBy;
import com.tokigames.api.flights.port.FlightProviderDataPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@AllArgsConstructor
public class FlightSearchService {

    private final FlightProviderDataPort flightProviderDataPort;

    public Flux<Flight> getFlights(String city, SortBy sortBy, Pageable page) {
        return getFilteredAndSortedFlights(city, sortBy, page);
    }

    private Flux<Flight> getFilteredAndSortedFlights(String city, SortBy sortBy, Pageable page) {
        Flux<Flight> flightFlux = flightProviderDataPort.getAllFlights();
        return flightFlux
                .filter(flight -> filterByCity(city, flight))
                .sort(sortBy.getComparator())
                .skip(page.getPageNumber() * page.getPageSize())
                .take(page.getPageSize());
    }

    private boolean filterByCity(String city, Flight flight) {
        return StringUtils.isEmpty(city)
                || flight.isLandedInThisCity(city);
    }


}
