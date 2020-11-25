package com.carlostambascia.controller;

import com.carlostambascia.model.City;
import com.carlostambascia.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/city/{id}")
    public ResponseEntity<City> get(@PathVariable("id") long id) {
        City city = cityService.getCity(id);
        return ResponseEntity.ok().body(city);
    }

    @GetMapping("/city")
    public ResponseEntity<List<City>> list() {
        List<City> list = cityService.list();
        return ResponseEntity.ok().body(list);
    }

}
