package com.geocoding.dao;

import com.geocoding.model.City;
import com.geocoding.model.GeocodeResponse;

import java.util.List;

public interface CityDao {

    City createCity(GeocodeResponse response);
    City getCity(String name);
    List<City> getCities();
}
