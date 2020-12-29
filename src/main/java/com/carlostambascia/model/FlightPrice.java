package com.carlostambascia.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "FlightPrice")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class FlightPrice {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal firstClass;
    private BigDecimal economy;
    private BigDecimal economyPlus;
}
