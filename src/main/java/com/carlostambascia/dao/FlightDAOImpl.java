package com.carlostambascia.dao;

import com.carlostambascia.model.Flight;
import com.carlostambascia.model.FlightPrice;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Named;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Named
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class FlightDAOImpl implements FlightDAO {
    private final SessionFactory sessionFactory;

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
    public Flight getFlightsById(Integer flightNumber) {
        final String hql = "from Flight f where f.flightNumber = ?";
        final Optional<Flight> flight = getCurrentSession().createQuery(hql)
                .setParameter(0, flightNumber)
                .uniqueResultOptional();
        return flight.orElse(null);
    }

    @Override
    public List<Flight> getFlightsFromDepartureByDate(String iataCodeDeparture, Date date) {
        final String hql = "from Flight f where f.scheduledDepartureDateTime = ? AND f.departureCity.iataCode = ?";
        return getCurrentSession().createQuery(hql)
                .setParameter(0, new Date(date.getTime() + (1000 * 60 * 60 * 24)))
                .setParameter(1, iataCodeDeparture)
                .list();
    }

    @Override
    public List<Flight> getFlightsFromDestinationByDate(String iataCodeDestination, Date date) {
        final String hql = "from Flight f where f.destinationCity.iataCode = ? AND f.scheduledDepartureDateTime = ?";
        return getCurrentSession().createQuery(hql)
                .setParameter(0, iataCodeDestination)
                .setParameter(1, new Date(date.getTime() + (1000 * 60 * 60 * 24)))
                .list();
    }

    @Override
    public List<Flight> getFlightsAirlineByDate(String airline, Date date) {
        final String hql = "from Flight f where f.airline = ? AND f.scheduledDepartureDateTime = ?";
        return getCurrentSession().createQuery(hql)
                .setParameter(0, airline)
                .setParameter(1, new Date(date.getTime() + (1000 * 60 * 60 * 24)))
                .list();
    }

    @Override
    public Integer addFlight(Flight flight) {
        Try.of(() -> sessionFactory.getCurrentSession().save(flight))
                .onFailure(e -> log.warn("There was an issue while saving the flight with the following exception -> " + e.getLocalizedMessage()));
        return flight.getFlightNumber();
    }

    @Override
    public FlightPrice getFlightPrice(Integer flightNumber) {
        final String hql = "select f.price from Flight f where f.flightNumber = ?";
        final Optional<FlightPrice> price = getCurrentSession().createQuery(hql)
                .setParameter(0, flightNumber)
                .uniqueResultOptional();
        return price.orElse(null);
    }

    @Override
    public void addFlightPrice(Integer flightNumber, FlightPrice price) {

    }

    @Override
    public void updateFlightPrice(Integer flightNumber, FlightPrice price) {

    }

    @Override
    public void removeFlightPrice(Integer flightNumber, String flightPriceId) {

    }
}
