package com.geocoding.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyData {
    @JsonProperty("temperature_2m_max")
    private Double[] maxTemperature;

    @JsonProperty("temperature_2m_min")
    private Double[] minTemperature;

    @JsonProperty("weathercode")
    private int[] weathercode;
}
