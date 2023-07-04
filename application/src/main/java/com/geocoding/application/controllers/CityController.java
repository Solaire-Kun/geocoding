package com.geocoding.application.controllers;

import com.geocoding.dao.CityDao;
import com.geocoding.model.City;
import com.geocoding.model.GeocodeResponse;
import com.geocoding.service.impl.GeocodingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(
        path = "/api",
        headers = {"Accept=application/json"},
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CityController {

    @Autowired
    private GeocodingServiceImpl geocodingService;

    @Autowired
    private CityDao cityDao;

    @GetMapping("/getCity")
    public City getCity(@RequestParam String cityName) {
        GeocodeResponse response = geocodingService.getCity(cityName).getBody();
        return cityDao.createCity(response);
    }

}
