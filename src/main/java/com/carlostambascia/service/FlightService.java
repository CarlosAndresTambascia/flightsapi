package com.carlostambascia.service;

import com.carlostambascia.model.Flight;
import com.carlostambascia.model.FlightPrice;

import java.util.Date;
import java.util.List;

public interface FlightService {
    List<Flight> getFlightsByDate(Date date);
    Flight getFlightsById(Integer flightNumber);
    List<Flight> getFlightsFromDepartureByDate(String iataCodeDeparture, Date date);
    List<Flight> getFlightsFromDestinationByDate(String iataCodeDestination, Date date);
    List<Flight> getFlightsAirlineByDate(String airline, Date date);
    Integer addFlight(Flight flight);
    FlightPrice getFlightPrice(Integer flightNumber);

    void addFlightPrice(Integer flightNumber, FlightPrice price);
    void updateFlightPrice(Integer flightNumber, FlightPrice price);
    void removeFlightPrice(Integer flightNumber, String flightPriceId);
}
