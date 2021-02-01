package com.carlostambascia.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "Flight")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flightNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private City departureCity;
    @OneToOne(cascade = CascadeType.ALL)
    private City destinationCity;
    private Date scheduledDepartureDateTime;
    private Date estimatedDepartureDateTime;
    private String airline;
    private String airlineCode;
    private Integer gate;
    @Column(name = "STATUS", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private FlightStatus status;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<FlightPrice> price;
}
