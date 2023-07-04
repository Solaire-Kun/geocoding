package com.geocoding.dao.impl;

import com.geocoding.dao.CityDao;
import com.geocoding.model.City;
import com.geocoding.model.GeocodeResponse;
import com.geocoding.model.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CityDaoImpl implements CityDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public City createCity(GeocodeResponse response) {
        List<Result> results = response.getResults();
        // Controlla se il risultato non è null o vuoto
        if (results != null && !results.isEmpty()) {
            Result firstResult = results.get(0);
            int id = firstResult.getId();
            String name = firstResult.getName();
            // Arrotondamento della longitudine e latidudine
            double longitude = Math.round(firstResult.getLongitude() * 100.0) / 100.0;
            double latitude = Math.round(firstResult.getLatitude() * 100.0) / 100.0;

            // Trova la città nel db e se non esiste viene creata
            City cityPersistence = entityManager.find(City.class, id);
            if(cityPersistence == null) {
                City newCity = new City();
                newCity.setId(id);
                newCity.setName(name);
                newCity.setLongitude(longitude);
                newCity.setLatitude(latitude);
                entityManager.persist(newCity);
                return newCity;
            }
        }
        return null;
    }

    @Override
    public City getCity(String name) {
        return entityManager.find(City.class, name);
    }

    @Override
    public List<City> getCities() {
        String hql = "SELECT c FROM City c";
        TypedQuery<City> query = entityManager.createQuery(hql, City.class);
        return query.getResultList();
    }
}
