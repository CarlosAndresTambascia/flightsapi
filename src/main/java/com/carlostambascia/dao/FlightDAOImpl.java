package com.carlostambascia.dao;

import com.carlostambascia.model.Flight;
import com.carlostambascia.model.FlightPrice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class FlightDAOImpl implements FlightDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Flight> getFlightsByDate(LocalDate date) {
        return null;//sessionFactory.getCurrentSession().find("from Flight").list();
    }

    @Override
    public Flight getFlightsById(String flightNumber) {
        return sessionFactory.getCurrentSession().get(Flight.class, flightNumber);
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
    public long addFlight(Flight flight) {
        //sessionFactory.getCurrentSession().save(flight);
        return 0;//flight.();
    }

    @Override
    public FlightPrice getFlightPrice(String flightNumber) {
        return null;
    }
}
