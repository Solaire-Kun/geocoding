package com.geocoding.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    @JsonProperty("hourly")
    private HourlyData hourlyData;

    @JsonProperty("daily")
    private DailyData dailyData;
}
