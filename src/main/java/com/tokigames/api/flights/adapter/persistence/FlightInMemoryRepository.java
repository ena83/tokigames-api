package com.tokigames.api.flights.adapter.persistence;

import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.domain.SortBy;
import com.tokigames.api.flights.port.FlightRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class FlightInMemoryRepository implements FlightRepositoryPort {

    private static Set<Flight> FLIGHTS_IN_MEMORY = new HashSet<>();

    @Override
    public void saveFlights(List<Flight> flights) {
        FLIGHTS_IN_MEMORY.addAll(flights);
    }

    @Override
    public void deleteAllFlights() {
        FLIGHTS_IN_MEMORY.clear();
    }

    /**
     * This will have performance issue if flights list is growing.
     * Performance will be better if it can be filtered,sorted and paged in DB (which is not implemented at the moment).
     */
    @Override
    public List<Flight> getFlights(String city, SortBy sortBy, Pageable page) {
        return (List<Flight>) FLIGHTS_IN_MEMORY
                .stream()
                .filter(flight -> filterByCity(city, flight))
                .skip(page.getPageNumber() * page.getPageSize())
                .sorted(sortBy.getComparator())
                .limit(page.getPageSize())
                .collect(Collectors.toList());
    }

    private boolean filterByCity(String city, Flight flight) {
        return StringUtils.isEmpty(city)
                || flight.isLandedInThisCity(city);
    }

}
