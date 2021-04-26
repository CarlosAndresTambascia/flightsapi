package com.carlostambascia.controller;

import com.carlostambascia.model.Flight;
import com.carlostambascia.model.FlightPrice;
import com.carlostambascia.model.Response;
import com.carlostambascia.service.FlightService;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@Controller
@RequestMapping(value = "/api/flight")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @PostMapping("/new/")
    public ResponseEntity<Response> save(@RequestBody Flight flight) {
        return Try.of(() -> flightService.addFlight(flight))
                .filter(Objects::nonNull)
                .map(flightNumber -> ResponseEntity.ok().body(new Response("The flight with id " + flightNumber + " was successfully saved.")))
                .getOrElse(ResponseEntity.badRequest().build());
    }

    @GetMapping(value = "/", params = "flightNumber")
    public ResponseEntity<Flight> getFlightsById(@RequestParam("flightNumber") Integer flightNumber) {
        return Try.of(() -> flightService.getFlightsById(flightNumber))
                .filter(Objects::nonNull)
                .map(flights -> ResponseEntity.ok().body(flights))
                .getOrElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/", params = {"destinationIataCode", "scheduledDepartureDateTime"})
    public ResponseEntity<List<Flight>> getFlightsFromDestinationByDate(@RequestParam("destinationIataCode") String destinationIataCode,
                                                             @RequestParam("scheduledDepartureDateTime") @DateTimeFormat(iso = DATE) Date scheduledDepartureDateTime) {
        return Try.of(() -> flightService.getFlightsFromDestinationByDate(destinationIataCode, scheduledDepartureDateTime))
                .filter(Objects::nonNull)
                .map(flights -> ResponseEntity.ok().body(flights))
                .getOrElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/", params = "scheduledDepartureDateTime")
    public ResponseEntity<List<Flight>> getByDepartureDateView(@RequestParam("scheduledDepartureDateTime") @DateTimeFormat(iso = DATE) Date scheduledDepartureDateTime) {
        return Try.of(() -> flightService.getFlightsByDate(scheduledDepartureDateTime))
                .filter(flights -> Objects.nonNull(flights) && !flights.isEmpty())
                .map(flights -> ResponseEntity.ok().body(flights))
                .getOrElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/view/", params = "scheduledDepartureDateTime")
    public ModelAndView getByDepartureDate(@RequestParam("scheduledDepartureDateTime") @DateTimeFormat(iso = DATE) Date scheduledDepartureDateTime) {
        final List<Flight> flightsFound = Try.of(() -> flightService.getFlightsByDate(scheduledDepartureDateTime))
                .filter(flights -> Objects.nonNull(flights) && !flights.isEmpty())
                .getOrElse(Collections.emptyList());
        final ModelAndView mav = new ModelAndView("listOfFlights");
        return mav.addObject("flightsFound", flightsFound);
    }

    @GetMapping(value = "/", params = {"scheduledDepartureDateTime", "departureIataCode"})
    public ResponseEntity<List<Flight>> getFromDepartureByDate(@RequestParam("departureIataCode") String departureIataCode,
                                                    @RequestParam("scheduledDepartureDateTime") @DateTimeFormat(iso = DATE) Date scheduledDepartureDateTime) {
        return Try.of(() -> flightService.getFlightsFromDepartureByDate(departureIataCode, scheduledDepartureDateTime))
                .filter(flights -> Objects.nonNull(flights) && !flights.isEmpty())
                .map(flights -> ResponseEntity.ok().body(flights))
                .getOrElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/", params = {"airline", "scheduledDepartureDateTime"})
    public ResponseEntity<List<Flight>> getFlightsAirlineByDate(@RequestParam("airline") String airline,
                                                     @RequestParam("scheduledDepartureDateTime") @DateTimeFormat(iso = DATE) Date scheduledDepartureDateTime) {
        return Try.of(() -> flightService.getFlightsAirlineByDate(airline, scheduledDepartureDateTime))
                .filter(flights -> Objects.nonNull(flights) && !flights.isEmpty())
                .map(flights -> ResponseEntity.ok().body(flights))
                .getOrElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/price/", params = "flightNumber")
    public ResponseEntity<FlightPrice> getFlightsPrice(@RequestParam("flightNumber") Integer flightNumber) {
        return Try.of(() -> flightService.getFlightPrice(flightNumber))
                .filter(Objects::nonNull)
                .map(flightPrice -> ResponseEntity.ok().body(flightPrice))
                .getOrElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/price/",  params = "flightNumber")
    public ResponseEntity<Response> addFlightPrice(@RequestParam("flightNumber") Integer flightNumber, @RequestBody FlightPrice price) {
        return Try.of(() -> flightService.addFlightPrice(flightNumber, price))
                .filter(Objects::nonNull)
                .map(numberOfPrices -> ResponseEntity.ok().body(new Response("The price was added successfully now you have " + numberOfPrices + " in the flight number " + flightNumber)))
                .getOrElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/price/",  params = {"flightNumber", "priceId"})
    public ResponseEntity<Response> updateFlightPrice(@RequestParam("flightNumber") Integer flightNumber, @RequestBody FlightPrice price, @RequestParam("priceId") Integer priceId) {
        return Try.of(() -> flightService.updateFlightPrice(flightNumber, price, priceId))
                .filter(Objects::nonNull)
                .map(id -> ResponseEntity.ok().body(new Response("The price with id " + priceId + " and flight number " + flightNumber + " was successfully updated.")))
                .getOrElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/price/",  params = {"flightNumber", "priceId"})
    public ResponseEntity<Response> removeFlightPrice(@RequestParam("flightNumber") Integer flightNumber, @RequestParam("priceId") Integer priceId) {
        return Try.of(() -> flightService.removeFlightPrice(flightNumber, priceId))
                .filter(Objects::nonNull)
                .map(id -> ResponseEntity.ok().body(new Response("The price with id " + id + " and flight number " + flightNumber + " was successfully removed.")))
                .getOrElse(ResponseEntity.notFound().build());
    }
}
