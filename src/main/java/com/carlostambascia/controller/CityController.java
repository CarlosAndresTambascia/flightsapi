package com.carlostambascia.controller;

import com.carlostambascia.model.City;
import com.carlostambascia.service.CityService;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/{iataCode}")
    public ResponseEntity<City> get(@PathVariable("iataCode")  String iataCode) {
        final City city = cityService.getCity(iataCode);
        return Option.of(city)
                .filter(Objects::nonNull)
                .map(city1 -> ResponseEntity.ok().body(city1))
                .getOrElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<List<City>> list() {
        final List<City> list = cityService.list();
        return ResponseEntity.ok().body(list);
    }

}
