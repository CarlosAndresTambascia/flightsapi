package com.carlostambascia.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Flight")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
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
