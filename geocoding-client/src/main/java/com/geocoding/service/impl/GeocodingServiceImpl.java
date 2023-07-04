package com.geocoding.service.impl;

import com.geocoding.model.GeocodeResponse;
import com.geocoding.service.GeocodingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeocodingServiceImpl implements GeocodingService {

    public static final String BASE_PATH = "https://geocoding-api.open-meteo.com/v1";

    @Override
    public ResponseEntity<GeocodeResponse> getCity(String cityName) {
        String url = BASE_PATH + "/search?name={cityName}&count=1&language=it&format=json";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<GeocodeResponse> response = restTemplate.getForEntity(url, GeocodeResponse.class, cityName);
        return response;
    }
}
