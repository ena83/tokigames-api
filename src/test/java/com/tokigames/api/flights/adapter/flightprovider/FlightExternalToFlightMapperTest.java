package com.tokigames.api.flights.adapter.flightprovider;

import com.tokigames.api.flights.adapter.flightprovider.model.FlightBusinessItemExternal;
import com.tokigames.api.flights.adapter.flightprovider.model.FlightCheapItemExternal;
import com.tokigames.api.flights.domain.Flight;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class FlightExternalToFlightMapperTest {

    @Test
    void shouldMapFlightCheapItemsExternalToFlights() {
        //given
        FlightCheapItemExternal item1 = new FlightCheapItemExternal("Singapore-Istanbul", 1558902656, 1558902656);
        FlightCheapItemExternal item2 = new FlightCheapItemExternal("Istanbul-Antalya", 1558902656, 1558902656);
        List<FlightCheapItemExternal> flightCheapItemsExternal = Lists.newArrayList(item1, item2);

        //when
        List<Flight> cheapFlights = FlightExternalToFlightMapper.toFlightsFromFlightCheapExternal(flightCheapItemsExternal);

        //then
        assertThat("Flight size not matched", cheapFlights.size(), is((2)));

        Flight cheapFlight1 = cheapFlights.get(0);
        Flight cheapFlight2 = cheapFlights.get(1);

        assertThat("Flight item 1 departure does not map as expected", cheapFlight1.getDeparture(), is(("Singapore")));
        assertThat("Flight item 1 arrival does not map as expected", cheapFlight1.getArrival(), is(("Istanbul")));
        assertThat("Flight item 1 departure day does not map as expected",
                cheapFlight1.getDepartureTime().atZone(ZoneId.of("UTC")).toLocalDate(), is(LocalDate.of(1970, 01, 19)));
        assertThat("Flight item 1 departure time(hour) does not map as expected",
                cheapFlight1.getDepartureTime().atZone(ZoneId.of("UTC")).getHour(),
                is((01)));
        assertThat("Flight item 1 departure time(minute) does not map as expected",
                cheapFlight1.getDepartureTime().atZone(ZoneId.of("UTC")).getMinute(),
                is((01)));

        assertThat("Flight item 2 departure does not map as expected", cheapFlight2.getDeparture(), is(("Istanbul")));
        assertThat("Flight item 2 arrival does not map as expected", cheapFlight2.getArrival(), is(("Antalya")));
        assertThat("Flight item 2 departure day does not map as expected",
                cheapFlight2.getDepartureTime().atZone(ZoneId.of("UTC")).toLocalDate(), is(LocalDate.of(1970, 01, 19)));
        assertThat("Flight item 2 departure time(hour) does not map as expected",
                cheapFlight2.getDepartureTime().atZone(ZoneId.of("UTC")).getHour(),
                is((01)));
        assertThat("Flight item 2 departure time(minute) does not map as expected",
                cheapFlight2.getDepartureTime().atZone(ZoneId.of("UTC")).getMinute(),
                is((01)));

    }

    @Test
    void shouldMapFlightBusinessItemsExternalToFlights() {
        //given
        FlightBusinessItemExternal item1 = new FlightBusinessItemExternal("Singapore", "Istanbul", 1558902656, 1558902656);
        FlightBusinessItemExternal item2 = new FlightBusinessItemExternal("Istanbul", "Antalya", 1558902656, 1558902656);
        List<FlightBusinessItemExternal> flightBusinessItemsExternal = Lists.newArrayList(item1, item2);

        //when
        List<Flight> businessFlights = FlightExternalToFlightMapper.toFlightsFromFlightBusinessExternal(flightBusinessItemsExternal);

        //then
        assertThat("Flight size not matched", businessFlights.size(), is((2)));

        Flight businessFlight1 = businessFlights.get(0);
        Flight businessFlight2 = businessFlights.get(1);

        assertThat("Flight item 1 departure does not map as expected", businessFlight1.getDeparture(), is(("Singapore")));
        assertThat("Flight item 1 arrival does not map as expected", businessFlight1.getArrival(), is(("Istanbul")));
        assertThat("Flight item 1 departure day does not map as expected",
                businessFlight1.getDepartureTime().atZone(ZoneId.of("UTC")).toLocalDate(), is(LocalDate.of(1970, 01, 19)));
        assertThat("Flight item 1 departure time(hour) does not map as expected",
                businessFlight1.getDepartureTime().atZone(ZoneId.of("UTC")).getHour(),
                is((01)));
        assertThat("Flight item 1 departure time(minute) does not map as expected",
                businessFlight1.getDepartureTime().atZone(ZoneId.of("UTC")).getMinute(),
                is((01)));

        assertThat("Flight item 2 departure does not map as expected", businessFlight2.getDeparture(), is(("Istanbul")));
        assertThat("Flight item 2 arrival does not map as expected", businessFlight2.getArrival(), is(("Antalya")));
        assertThat("Flight item 2 departure day does not map as expected",
                businessFlight2.getDepartureTime().atZone(ZoneId.of("UTC")).toLocalDate(), is(LocalDate.of(1970, 01, 19)));
        assertThat("Flight item 2 departure time(hour) does not map as expected",
                businessFlight2.getDepartureTime().atZone(ZoneId.of("UTC")).getHour(),
                is((01)));
        assertThat("Flight item 2 departure time(minute) does not map as expected",
                businessFlight2.getDepartureTime().atZone(ZoneId.of("UTC")).getMinute(),
                is((01)));
    }
}