package com.carlostambascia.service;

import com.carlostambascia.dao.CityDAO;
import com.carlostambascia.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDAO cityDAO;

    @Override
    public City getCity(long id) {
        return cityDAO.getCity(id);
    }

    @Override
    public List<City> list() {
        return cityDAO.list();
    }
}
