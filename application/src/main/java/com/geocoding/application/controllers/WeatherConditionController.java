package com.geocoding.application.controllers;

import com.geocoding.dao.WeatherConditionDao;
import com.geocoding.model.DailyData;
import com.geocoding.model.HourlyData;
import com.geocoding.model.WeatherCondition;
import com.geocoding.model.WeatherResponse;
import com.geocoding.service.impl.WeatherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(
        path = "/api",
        headers = {"Accept=application/json"},
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class WeatherConditionController {

    @Autowired
    private WeatherServiceImpl weatherService;

    @Autowired
    private WeatherConditionDao weatherConditionDao;

    @GetMapping("/getWeather")
    public WeatherCondition getWeather(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam String date,
            @RequestParam int stayId) {
        WeatherResponse response = weatherService.getWeather(latitude, longitude, date).getBody();

        // Prendi l'umidità e temperatura minima e massima
        HourlyData hourlyData = response.getHourlyData();
        DailyData dailyData = response.getDailyData();

        // Salva i dati come array
        int[] relativeHumidity = hourlyData.getRelativeHumidity();
        Double[] responseMinTemperature = dailyData.getMinTemperature();
        Double[] responseMaxTemperature = dailyData.getMaxTemperature();
        int[] responseWeathercode = dailyData.getWeathercode();

        return weatherConditionDao.createWeather(relativeHumidity, responseMinTemperature, responseMaxTemperature, responseWeathercode, stayId);
    }

}
