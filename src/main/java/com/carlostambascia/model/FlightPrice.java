package com.carlostambascia.model;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "FlightPrice")
@AllArgsConstructor
public class FlightPrice {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal firstClass;
    private BigDecimal economy;
    private BigDecimal economyPlus;
}
