package com.tokigames.api.flights.domain;

import java.util.Comparator;

public class FlightDepartureTimeComparator implements Comparator<Flight> {
    @Override
    public int compare(Flight o1, Flight o2) {
        return o1.getDepartureTime().compareTo(o2.getDepartureTime());
    }
}
