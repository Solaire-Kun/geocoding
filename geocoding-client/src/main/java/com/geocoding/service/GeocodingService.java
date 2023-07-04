package com.geocoding.service;

import com.geocoding.model.GeocodeResponse;
import org.springframework.http.ResponseEntity;

public interface GeocodingService {

    public ResponseEntity<GeocodeResponse> getCity(String cityName);
}
