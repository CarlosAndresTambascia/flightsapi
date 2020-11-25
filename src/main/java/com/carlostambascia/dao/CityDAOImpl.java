package com.carlostambascia.dao;

import com.carlostambascia.model.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityDAOImpl implements CityDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public City getCity(long id) {
        return getCurrentSession().get(City.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<City> list() {
        return getCurrentSession().createQuery("from City").list();
    }
}
