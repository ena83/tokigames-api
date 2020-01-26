package com.tokigames.api.flights.domain;

import lombok.Getter;

import java.util.Comparator;

public enum SortBy {
    DEPARTURE_CITY(new FlightDepartureComparator()),
    ARRIVAL_CITY(new FlightArrivalComparator());

    @Getter
    private Comparator comparator;

    SortBy(Comparator comparator) {
        this.comparator = comparator;
    }
}
