package com.geocoding.service.impl;

import com.geocoding.model.WeatherResponse;
import com.geocoding.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {
    public static final String BASE_PATH = "https://api.open-meteo.com/v1";

    @Override
    public ResponseEntity<WeatherResponse> getWeather(Double latitude, Double longitude, String date) {
        String url = BASE_PATH + "/forecast?latitude={latitude}&longitude={longitude}&hourly=relativehumidity_2m&daily=weathercode,temperature_2m_max,temperature_2m_min&forecast_days=1&timezone=auto&start_date={date}&end_date={date}&format=json";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class, latitude, longitude, date, date);
        return response;
    }
}
