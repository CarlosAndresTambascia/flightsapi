package com.carlostambascia.service;

import com.carlostambascia.dao.FlightDAO;
import com.carlostambascia.model.Flight;
import com.carlostambascia.model.FlightPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class FlightServiceImpl implements FlightService{
    @Autowired
    private FlightDAO flightDAO;

    @Override
    public List<Flight> getFlightsByDate(LocalDate date) {
        return flightDAO.getFlightsByDate(date);
    }

    @Override
    public Flight getFlightsById(String flightNumber) {
        return null;
    }

    @Override
    public List<Flight> getFlightsFromDepartureByDate(String iataCodeDeparture, LocalDate date) {
        return null;
    }

    @Override
    public List<Flight> getFlightsFromDestinationByDate(String iataCodeDestination, LocalDate date) {
        return null;
    }

    @Override
    public List<Flight> getFlightsAirlineByDate(String airline, LocalDate date) {
        return null;
    }

    @Override
    public String addFlight(Flight flight) {
        return flightDAO.addFlight(flight);
    }

    @Override
    public FlightPrice getFlightPrice(String flightNumber) {
        return null;
    }
}
