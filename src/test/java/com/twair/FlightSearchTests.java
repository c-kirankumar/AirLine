package com.twair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class FlightSearchTests {
    private String source;
    private String destination;
    private  int noOfSeats;
    private FlightSearch allFlights;

    @Before
    public void setUp() throws Exception {
        source = "TestSource";
        destination = "TestDestination";
        noOfSeats=1;
        Plane plane1 = new Plane("type1", 10);
        Flight flight1 = new Flight("F001", source, destination, plane1);
        Flight flight2 = new Flight("F002", "TestSource1", destination, plane1);
        Flight flight3 = new Flight("F003", source, destination, plane1);
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight1);
        flightList.add(flight2);
        flightList.add(flight3);
        allFlights = new FlightSearch(flightList);
    }

    @Test
    public void shouldReturnListOfFlightsForMatchingSourceDestination() throws Exception {
        List<Flight> flights = allFlights.byCriteria(source, destination,noOfSeats).getFlightList();
        Assert.assertEquals(source, flights.get(0).getSource());
        Assert.assertEquals(destination, flights.get(0).getDestination());
        Assert.assertEquals(source, flights.get(1).getSource());
        Assert.assertEquals(destination, flights.get(1).getDestination());
        Assert.assertEquals(2, flights.size());
    }

    @Test
    public void shouldReturnZeroFlightsIfSeatsAreNotAvailableFromSourceDestination() throws Exception {
        List<Flight> flights = allFlights.byCriteria(source, destination,200).getFlightList();
        Assert.assertEquals(0,flights.size());
    }


    @Test(expected=IllegalArgumentException.class)
    public void shouldMandateSource() throws Exception {
        allFlights.byCriteria(null, destination,noOfSeats);
    }

    @Test(expected=IllegalArgumentException.class)
    public void sourceCannotBeEmpty() throws Exception {
        allFlights.byCriteria("", destination,noOfSeats);
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldMandateDestination() throws Exception {
        allFlights.byCriteria(source, null,noOfSeats);
    }

    @Test(expected=IllegalArgumentException.class)
    public void destinationCannotBeEmpty() throws Exception {
        allFlights.byCriteria(source, "",noOfSeats);
    }

    @Test(expected=IllegalArgumentException.class)
    public void noOfSeatsCannotBeZero() throws Exception {
        allFlights.byCriteria(source, destination,0);
    }


    @Test(expected=IllegalArgumentException.class)
    public void noOfSeatsCannotBeNegative() throws Exception {
        allFlights.byCriteria(source, destination,-1);
    }

}
