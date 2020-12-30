package com.carlostambascia.dao;

import com.carlostambascia.model.Flight;
import com.carlostambascia.model.FlightPrice;

import java.time.LocalDate;
import java.util.List;

public interface FlightDAO {
    List<Flight> getFlightsByDate(LocalDate date);
    Flight getFlightsById(String flightNumber);
    List<Flight> getFlightsFromDepartureByDate(String iataCodeDeparture, LocalDate date);
    List<Flight> getFlightsFromDestinationByDate(String iataCodeDestination, LocalDate date);
    List<Flight> getFlightsAirlineByDate(String airline, LocalDate date);
    String addFlight(Flight flight);
    FlightPrice getFlightPrice(String flightNumber);
}
