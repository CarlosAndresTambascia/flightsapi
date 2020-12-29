package com.carlostambascia.controller;

import com.carlostambascia.model.City;
import com.carlostambascia.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/{id}")
    public ResponseEntity<City> get(@PathVariable("id")  long id) {
        City city = cityService.getCity(id);
        return ResponseEntity.ok().body(city);
    }

    @GetMapping("/")
    public ResponseEntity<List<City>> list() {
        List<City> list = cityService.list();
        return ResponseEntity.ok().body(list);
    }

}
