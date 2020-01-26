package com.tokigames.api.flights.adapter.web;

import com.tokigames.api.flights.application.FlightSearchService;
import com.tokigames.api.flights.domain.Flight;
import com.tokigames.api.flights.domain.SortBy;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static com.tokigames.api.flights.domain.PageSupport.DEFAULT_PAGE_SIZE;
import static com.tokigames.api.flights.domain.PageSupport.FIRST_PAGE_NUM;

@RestController
@AllArgsConstructor
public class FlightController {

    private final FlightSearchService flightSearchService;

    @GetMapping(value = "/flights", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Flight> getFlights(
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "sortBy") SortBy sortBy,
            @RequestParam(name = "page", defaultValue = FIRST_PAGE_NUM) int page,
            @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size) {
        return flightSearchService.getFlights(city, sortBy, PageRequest.of(page, size));
    }
}
