package com.carlostambascia.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Flight")
@AllArgsConstructor
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String flightNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private City departureCity;
    @OneToOne(cascade = CascadeType.ALL)
    private City destinationCity;
    private LocalDate scheduledDepartureDateTime;
    private LocalDate estimatedDepartureDateTime;
    private String airline;
    private String airlineCode;
    private Integer gate;
    @Column(name = "STATUS", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private FlightStatus status;
    @OneToOne(cascade = CascadeType.ALL)
    private FlightPrice price;
}
