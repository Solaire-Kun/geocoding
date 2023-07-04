package com.geocoding.dao;

import com.geocoding.model.WeatherCondition;

public interface WeatherConditionDao {
    WeatherCondition createWeather(int[] humidity, Double[] minTemperature, Double[] maxTemperature, int[] weathercode, int stayId);
}
