package com.carlostambascia.dao;

import com.carlostambascia.model.City;

import java.util.List;

public interface CityDAO {
    City getCity(String iataCode);
    List<City> list();
}
