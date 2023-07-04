package com.geocoding.service;

import com.geocoding.model.WeatherResponse;
import org.springframework.http.ResponseEntity;

public interface WeatherService {

    public ResponseEntity<WeatherResponse> getWeather(Double latitude, Double longitude, String date);
}
