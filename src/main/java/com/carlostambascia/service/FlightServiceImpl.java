package com.carlostambascia.service;

import com.carlostambascia.dao.FlightDAO;
import com.carlostambascia.model.Flight;
import com.carlostambascia.model.FlightPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightDAO flightDAO;

    @Override
    public List<Flight> getFlightsByDate(Date date) {
        return flightDAO.getFlightsByDate(date);
    }

    @Override
    public Flight getFlightsById(Integer flightNumber) {
        return flightDAO.getFlightsById(flightNumber);
    }

    @Override
    public List<Flight> getFlightsFromDepartureByDate(String iataCodeDeparture, Date date) {
        return flightDAO.getFlightsFromDepartureByDate(iataCodeDeparture, date);
    }

    @Override
    public List<Flight> getFlightsFromDestinationByDate(String iataCodeDestination, Date date) {
        return flightDAO.getFlightsFromDestinationByDate(iataCodeDestination, date);
    }

    @Override
    public List<Flight> getFlightsAirlineByDate(String airline, Date date) {
        return flightDAO.getFlightsAirlineByDate(airline, date);
    }

    @Override
    public Integer addFlight(Flight flight) {
        return flightDAO.addFlight(flight);
    }

    @Override
    public FlightPrice getFlightPrice(String flightNumber) {
        return null;
    }

    @Override
    public void addFlightPrice(String flightNumber, FlightPrice price) {

    }

    @Override
    public void updateFlightPrice(String flightNumber, FlightPrice price) {

    }

    @Override
    public void removeFlightPrice(String flightNumber, String flightPriceId) {

    }
}
