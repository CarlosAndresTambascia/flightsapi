package com.carlostambascia.model;

import lombok.AllArgsConstructor;

import javax.persistence.Entity;

@Entity(name = "City")
@AllArgsConstructor
public class City {
    private String name;
    private String iataCode;
}
