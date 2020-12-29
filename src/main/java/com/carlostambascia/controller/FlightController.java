package com.carlostambascia.controller;

import com.carlostambascia.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
}
