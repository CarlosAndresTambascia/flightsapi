package com.carlostambascia.service;

import com.carlostambascia.dao.CityDAO;
import com.carlostambascia.model.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityDAO cityDAO;

    @Override
    public City getCity(String iataCode) {
        return cityDAO.getCity(iataCode);
    }

    @Override
    public List<City> list() {
        return cityDAO.list();
    }
}
