package com.carlostambascia.controller;

import com.carlostambascia.model.Flight;
import com.carlostambascia.model.FlightPrice;
import com.carlostambascia.service.FlightService;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@RestController
@RequestMapping(value = "/api/flight")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Flight flight) {
        return Try.of(() -> flightService.addFlight(flight))
                .filter(Objects::nonNull)
                .map(flightNumber -> ResponseEntity.ok().body("The flight with id " + flightNumber + " was successfully saved."))
                .getOrElse(ResponseEntity.badRequest().build());
    }

    @GetMapping(value = "/", params = "flightNumber")
    public ResponseEntity<?> getFlightsById(@RequestParam("flightNumber") Integer flightNumber) {
        return Try.of(() -> flightService.getFlightsById(flightNumber))
                .filter(Objects::nonNull)
                .map(flights -> ResponseEntity.ok().body(flights))
                .getOrElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/", params = {"destinationIataCode", "scheduledDepartureDateTime"})
    public ResponseEntity<?> getFlightsFromDestinationByDate(@RequestParam("destinationIataCode") String destinationIataCode,
                                                             @RequestParam("scheduledDepartureDateTime") @DateTimeFormat(iso = DATE) Date scheduledDepartureDateTime) {
        return Try.of(() -> flightService.getFlightsFromDestinationByDate(destinationIataCode, scheduledDepartureDateTime))
                .filter(Objects::nonNull)
                .map(flights -> ResponseEntity.ok().body(flights))
                .getOrElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/", params = "scheduledDepartureDateTime")
    public ResponseEntity<?> getByDepartureDate(@RequestParam("scheduledDepartureDateTime") @DateTimeFormat(iso = DATE) Date scheduledDepartureDateTime) {
        return Try.of(() -> flightService.getFlightsByDate(scheduledDepartureDateTime))
                .filter(flights -> Objects.nonNull(flights) && !flights.isEmpty())
                .map(flights -> ResponseEntity.ok().body(flights))
                .getOrElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/", params = {"scheduledDepartureDateTime", "departureIataCode"})
    public ResponseEntity<?> getFromDepartureByDate(@RequestParam("departureIataCode") String departureIataCode,
                                                    @RequestParam("scheduledDepartureDateTime") @DateTimeFormat(iso = DATE) Date scheduledDepartureDateTime) {
        return Try.of(() -> flightService.getFlightsFromDepartureByDate(departureIataCode, scheduledDepartureDateTime))
                .filter(flights -> Objects.nonNull(flights) && !flights.isEmpty())
                .map(flights -> ResponseEntity.ok().body(flights))
                .getOrElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/", params = {"airline", "scheduledDepartureDateTime"})
    public ResponseEntity<?> getFlightsAirlineByDate(@RequestParam("airline") String airline,
                                                     @RequestParam("scheduledDepartureDateTime") @DateTimeFormat(iso = DATE) Date scheduledDepartureDateTime) {
        return Try.of(() -> flightService.getFlightsAirlineByDate(airline, scheduledDepartureDateTime))
                .filter(flights -> Objects.nonNull(flights) && !flights.isEmpty())
                .map(flights -> ResponseEntity.ok().body(flights))
                .getOrElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/price/", params = "flightNumber")
    public ResponseEntity<?> getFlightsPrice(@RequestParam("flightNumber") Integer flightNumber) {
        return Try.of(() -> flightService.getFlightPrice(flightNumber))
                .filter(Objects::nonNull)
                .map(flights -> ResponseEntity.ok().body(flights))
                .getOrElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/price/",  params = "flightNumber")
    public ResponseEntity<?> addFlightPrice(@RequestParam("flightNumber") Integer flightNumber, @RequestBody FlightPrice price) {
        return Try.of(() -> flightService.addFlightPrice(flightNumber, price))
                .filter(Objects::nonNull)
                .map(id -> ResponseEntity.ok().body("The price of the flight number " + id + " was successfully saved."))
                .getOrElse(ResponseEntity.badRequest().build());
    }
}
