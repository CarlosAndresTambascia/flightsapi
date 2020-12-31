package com.carlostambascia.dao;

import com.carlostambascia.model.Flight;
import com.carlostambascia.model.FlightPrice;

import java.util.Date;
import java.util.List;

public interface FlightDAO {
    List<Flight> getFlightsByDate(Date date);
    Flight getFlightsById(String flightNumber);
    List<Flight> getFlightsFromDepartureByDate(String iataCodeDeparture, Date date);
    List<Flight> getFlightsFromDestinationByDate(String iataCodeDestination, Date date);
    List<Flight> getFlightsAirlineByDate(String airline, Date date);
    String addFlight(Flight flight);
    FlightPrice getFlightPrice(String flightNumber);
    void addFlightPrice(String flightNumber, FlightPrice price);
    void updateFlightPrice(String flightNumber, FlightPrice price);
    void removeFlightPrice(String flightNumber, String flightPriceId);
}
