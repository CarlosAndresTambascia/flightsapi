package com.carlostambascia.dao;

import com.carlostambascia.model.Flight;
import com.carlostambascia.model.FlightPrice;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Slf4j
@Repository
@SuppressWarnings("unchecked")
public class FlightDAOImpl implements FlightDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Flight> getFlightsByDate(Date date) {
        final String hql = "from Flight f where f.scheduledDepartureDateTime = ?";
        return getCurrentSession().createQuery(hql)
                .setParameter(0, new Date(date.getTime() + (1000 * 60 * 60 * 24)))
                .list();
    }

    @Override
    public Flight getFlightsById(String flightNumber) {
        return sessionFactory.getCurrentSession().get(Flight.class, flightNumber);
    }

    @Override
    public List<Flight> getFlightsFromDepartureByDate(String iataCodeDeparture, Date date) {
        final String hql = "from Flight f where f.scheduledDepartureDateTime = ? AND f.iataCode = ?";
        return getCurrentSession().createQuery(hql)
                .setParameter(0, date)
                .setParameter(1, iataCodeDeparture)
                .list();
    }

    @Override
    public List<Flight> getFlightsFromDestinationByDate(String iataCodeDestination, Date date) {
        //TODO: fix this query
        final String hql = "from Flight f where f.departureCity = ? AND f.iataCode = ?";
        return getCurrentSession().createQuery(hql)
                .setParameter(0, date)
                .setParameter(1, iataCodeDestination)
                .list();
    }

    @Override
    public List<Flight> getFlightsAirlineByDate(String airline, Date date) {
        final String hql = "from Flight f where f.airline = ? AND f.scheduledDepartureDateTime = ?";
        return getCurrentSession().createQuery(hql)
                .setParameter(0, airline)
                .setParameter(1, date)
                .list();
    }

    @Override
    public String addFlight(Flight flight) {
        Try.of(() -> sessionFactory.getCurrentSession().save(flight))
                .onFailure(e -> log.warn("There was an issue while saving the flight with the following exception -> " + e.getLocalizedMessage()));
        return flight.getFlightNumber();
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
