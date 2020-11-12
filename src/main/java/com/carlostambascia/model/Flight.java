package com.carlostambascia.model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity(name = "Flight")
public class Flight {
    private final City departureCity;
    private final City destinationCity;
    private final LocalDate scheduledDepartureDateTime;
    private final LocalDate estimatedDepartureDateTime;
    private final String flightNumber;
    private final String airline;
    private final String airlineCode;
    private final Integer gate;
    private final Integer gate;
}
