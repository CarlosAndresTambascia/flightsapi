package com.carlostambascia.service;

import com.carlostambascia.model.Flight;
import com.carlostambascia.model.FlightPrice;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    List<Flight> getFlightsByDate(LocalDate date);
    Flight getFlightsById(String flightNumber);
    List<Flight> getFlightsFromDepartureByDate(String iataCodeDeparture, LocalDate date);
    List<Flight> getFlightsFromDestinationByDate(String iataCodeDestination, LocalDate date);
    List<Flight> getFlightsAirlineByDate(String airline, LocalDate date);
    String addFlight(Flight flight);
    FlightPrice getFlightPrice(String flightNumber);

    void addFlightPrice(String flightNumber, FlightPrice price);
    void updateFlightPrice(String flightNumber, FlightPrice price);
    void removeFlightPrice(String flightNumber, String flightPriceId);
}
