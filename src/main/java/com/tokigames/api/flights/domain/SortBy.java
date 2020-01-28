package com.tokigames.api.flights.domain;

import lombok.Getter;

import java.util.Comparator;

public enum SortBy {
    DEPARTURE_CITY(Comparator.comparing(Flight::getDeparture)),
    ARRIVAL_CITY(Comparator.comparing(Flight::getArrival)),
    DEPARTURE_CITY_AND_TIME(Comparator.comparing(Flight::getDeparture)
            .thenComparing(Flight::getDepartureTime));

    @Getter
    private Comparator comparator;

    SortBy(Comparator comparator) {
        this.comparator = comparator;
    }

    public String getName() {
        return this.name();
    }
}
