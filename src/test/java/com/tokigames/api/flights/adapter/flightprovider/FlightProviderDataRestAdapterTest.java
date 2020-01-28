package com.tokigames.api.flights.adapter.flightprovider;

import com.tokigames.api.flights.domain.Flight;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:/application-test.properties"})
class FlightProviderDataRestAdapterTest {

    private static final int PORT = 9999;

    private MockWebServer mockWebServer = new MockWebServer();

    @Autowired
    private FlightProviderDataRestAdapter flightProviderDataRestAdapter;

    @BeforeEach
    public void setup() throws IOException {
        mockWebServer.start(PORT);
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void shouldReturnAllCheapFlights() throws InterruptedException {
        //given
        MockResponse cheapFlightMockResponse = cheapFlightsMockResponse();
        mockWebServer.enqueue(cheapFlightMockResponse);

        //when
        List<Flight> cheapFlights = flightProviderDataRestAdapter.getAllCheapFlights().block();

        mockWebServer.takeRequest();

        //then
        assertThat(cheapFlights.size(), is(2));

        Flight cheapFlight1 = cheapFlights.get(0);
        assertThat(cheapFlight1.getDeparture(), is("Cruz del Eje"));
        assertThat(cheapFlight1.getArrival(), is("Antalya"));
        assertThat("Flight item 1 departure day does not map as expected",
                cheapFlight1.getDepartureTime().atZone(ZoneId.of("UTC")).toLocalDate(), is(LocalDate.of(1970, 01, 19)));
        assertThat("Flight item 1 departure time(hour) does not map as expected",
                cheapFlight1.getDepartureTime().atZone(ZoneId.of("UTC")).getHour(),
                is((01)));
        assertThat("Flight item 1 departure time(minute) does not map as expected",
                cheapFlight1.getDepartureTime().atZone(ZoneId.of("UTC")).getMinute(),
                is((01)));

        Flight cheapFlight2 = cheapFlights.get(1);
        assertThat("Flight item 2 departure does not map as expected", cheapFlight2.getDeparture(), is(("Cruz del Eje")));
        assertThat("Flight item 2 arrival does not map as expected", cheapFlight2.getArrival(), is(("Tizi")));
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
    void shouldReturnAllBusinessFlights() throws InterruptedException {

        //given
        MockResponse businessFlightMockResponse = businessFlightMockResponse();
        mockWebServer.enqueue(businessFlightMockResponse);

        //when
        List<Flight> businessFlights = flightProviderDataRestAdapter.getAllBusinessFlights().block();

        mockWebServer.takeRequest();

        //then
        assertThat(businessFlights.size(), is(2));

        Flight businessFlight1 = businessFlights.get(0);
        assertThat(businessFlight1.getDeparture(), is("Ankara"));
        assertThat(businessFlight1.getArrival(), is("Antalya"));
        assertThat("Flight item 1 departure day does not map as expected",
                businessFlight1.getDepartureTime().atZone(ZoneId.of("UTC")).toLocalDate(), is(LocalDate.of(1970, 01, 19)));
        assertThat("Flight item 1 departure time(hour) does not map as expected",
                businessFlight1.getDepartureTime().atZone(ZoneId.of("UTC")).getHour(),
                is((01)));
        assertThat("Flight item 1 departure time(minute) does not map as expected",
                businessFlight1.getDepartureTime().atZone(ZoneId.of("UTC")).getMinute(),
                is((47)));

        Flight cheapFlight2 = businessFlights.get(1);
        assertThat("Flight item 2 departure does not map as expected", cheapFlight2.getDeparture(), is(("Cruz del Eje")));
        assertThat("Flight item 2 arrival does not map as expected", cheapFlight2.getArrival(), is(("Tizi")));
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
    void shouldReturnAllFlights() throws InterruptedException {

        //given
        MockResponse cheapFlightMockResponse = cheapFlightsMockResponse();
        MockResponse businessFlightMockResponse = businessFlightMockResponse();

        mockWebServer.enqueue(cheapFlightMockResponse);
        mockWebServer.enqueue(businessFlightMockResponse);

        //when
        List<Flight> allFlights = flightProviderDataRestAdapter.getAllFlights().collectList().block();

        mockWebServer.takeRequest();

        //then
        assertThat(allFlights.size(), is(4));
    }

    @Test
    void shouldReturnOnlyCheapFlights_BusinessFlightsError() throws InterruptedException {

        //given
        MockResponse cheapFlightMockResponse = cheapFlightsMockResponse();
        MockResponse flightMockErrorResponse = flightMockErrorResponse();

        mockWebServer.enqueue(cheapFlightMockResponse);
        mockWebServer.enqueue(flightMockErrorResponse);

        //when
        List<Flight> allFlights = flightProviderDataRestAdapter.getAllFlights().collectList().block();

        mockWebServer.takeRequest();

        //then
        assertThat(allFlights.size(), is(2));
    }

    @Test
    void shouldReturnOnlyBusinessFlights_CheapFlightsError() throws InterruptedException {

        //given
        MockResponse flightMockErrorResponse = flightMockErrorResponse();
        MockResponse businessFlightsMockResponse = businessFlightMockResponse();

        mockWebServer.enqueue(flightMockErrorResponse);
        mockWebServer.enqueue(businessFlightsMockResponse);

        //when
        List<Flight> allFlights = flightProviderDataRestAdapter.getAllFlights().collectList().block();

        mockWebServer.takeRequest();

        //then
        assertThat(allFlights.size(), is(2));
    }

    private MockResponse cheapFlightsMockResponse() {
        return new MockResponse()
                .setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setBody("{\n" +
                        "    \"data\": [\n" +
                        "        {\n" +
                        "            \"route\": \"Cruz del Eje-Antalya\",\n" +
                        "            \"departure\": 1558902656,\n" +
                        "            \"arrival\": 1558902656\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"route\": \"Cruz del Eje-Tizi\",\n" +
                        "            \"departure\": 1558902656,\n" +
                        "            \"arrival\": 1558902656\n" +
                        "        }]" +
                        "}");
    }

    private MockResponse businessFlightMockResponse() {
        return new MockResponse()
                .setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setBody("{\n" +
                        "    \"data\": [\n" +
                        "        {\n" +
                        "            \"departure\": \"Ankara\",\n" +
                        "            \"arrival\": \"Antalya\",\n" +
                        "            \"departureTime\": 1561627856,\n" +
                        "            \"arrivalTime\": 1564410656\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"departure\": \"Cruz del Eje\",\n" +
                        "            \"arrival\": \"Tizi\",\n" +
                        "            \"departureTime\": 1558902656,\n" +
                        "            \"arrivalTime\": 1558902656\n" +
                        "        }]" +
                        "}");
    }

    private MockResponse flightMockErrorResponse() {
        return new MockResponse()
                .setResponseCode(500)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

}