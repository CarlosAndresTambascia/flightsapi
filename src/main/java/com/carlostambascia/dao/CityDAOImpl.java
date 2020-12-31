package com.carlostambascia.dao;

import com.carlostambascia.model.City;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Getter
@SuppressWarnings("unchecked")
public class CityDAOImpl implements CityDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public City getCity(String iataCode) {
        final String hql = "from City c where c.iataCode = ?";
        final Optional<City> city = getCurrentSession().createQuery(hql)
                .setParameter(0, iataCode)
                .uniqueResultOptional();
        return city.orElse(null);
    }

    @Override
    public List<City> list() {
        return getCurrentSession().createQuery("from City").list();
    }
}
