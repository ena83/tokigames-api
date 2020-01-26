package com.tokigames.api.flights.application;

import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.domain.SortBy;
import com.tokigames.api.flights.port.FlightRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FlightSearchService {

    private final FlightRepositoryPort flightRepositoryPort;

    /**
     * This will have performance issue if flights list is growing.
     * Performance will be better if it can be filtered,sorted and paged in Repository layer (which is not implemented at the moment).
     */
    public Flux<Flight> getFlights(String city, SortBy sortBy, Pageable page) {
        List<Flight> allFlights = getFilteredFlight(city, page);
        allFlights.sort(sortBy.getComparator());
        return Flux.fromIterable(allFlights);
    }

    private List<Flight> getFilteredFlight(String city, Pageable page) {
        return flightRepositoryPort.getAllFlights()
                .stream()
                .filter(flight -> (StringUtils.isEmpty(city)
                        || flight.isLandedInThisCity(city)))
                .skip(page.getPageNumber() * page.getPageSize())
                .limit(page.getPageSize())
                .collect(Collectors.toList());
    }


}
