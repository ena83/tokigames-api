package com.tokigames.api.flights.domain;

import java.util.Comparator;

public class FlightDepartureComparator implements Comparator<Flight> {
    @Override
    public int compare(Flight o1, Flight o2) {
        return o1.getDeparture().compareTo(o2.getDeparture());
    }
}
