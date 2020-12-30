package com.carlostambascia.controller;

import com.carlostambascia.model.Flight;
import com.carlostambascia.service.FlightService;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Flight flight) {
        return Try.of(() -> flightService.addFlight(flight))
                .filter(Objects::nonNull)
                .map(flightNumber -> ResponseEntity.ok().body("The flight with id" + flightNumber + "was successfully saved."))
                .getOrElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{date}")
    public ResponseEntity<?> getByDepartureDate(@PathVariable(name = "date") String date) {
        return Try.of(() -> flightService.getFlightsByDate(LocalDate.parse(date)))
                .filter(flights -> Objects.nonNull(flights) && !flights.isEmpty())
                .map(flights -> ResponseEntity.ok().body(flights))
                .getOrElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{iataCode}/{scheduledDepartureDateTime}")
    public ResponseEntity<?> getFromDepartureByDate(@PathVariable(name = "iataCode") String iataCode, @PathVariable(name = "scheduledDepartureDateTime") String scheduledDepartureDateTime) {
        return Try.of(() -> flightService.getFlightsFromDepartureByDate(iataCode, LocalDate.parse(scheduledDepartureDateTime)))
                .filter(flights -> Objects.nonNull(flights) && !flights.isEmpty())
                .map(flights -> ResponseEntity.ok().body(flights))
                .getOrElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{airline}/{scheduledDepartureDateTime}")
    public ResponseEntity<?> getFlightsAirlineByDate(@PathVariable(name = "airline") String airline, @PathVariable(name = "scheduledDepartureDateTime") String scheduledDepartureDateTime) {
        return Try.of(() -> flightService.getFlightsAirlineByDate(airline, LocalDate.parse(scheduledDepartureDateTime)))
                .filter(flights -> Objects.nonNull(flights) && !flights.isEmpty())
                .map(flights -> ResponseEntity.ok().body(flights))
                .getOrElse(ResponseEntity.notFound().build());
    }
}
