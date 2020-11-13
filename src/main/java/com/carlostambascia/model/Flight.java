package com.carlostambascia.model;

import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity(name = "Flight")
@AllArgsConstructor
public class Flight {
    private City departureCity;
    private City destinationCity;
    private LocalDate scheduledDepartureDateTime;
    private LocalDate estimatedDepartureDateTime;
    private String flightNumber;
    private String airline;
    private String airlineCode;
    private Integer gate;
    private FlightStatus status;
    private FlightPrice price;
}
