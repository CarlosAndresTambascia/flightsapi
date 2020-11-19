package com.carlostambascia.model;

import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "City")
@AllArgsConstructor
public class City {
    @Id
    private String iataCode;
    private String name;
}
