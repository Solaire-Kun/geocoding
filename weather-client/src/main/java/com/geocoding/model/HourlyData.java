package com.geocoding.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HourlyData {
    @JsonProperty("relativehumidity_2m")
    private int[] relativeHumidity;
}
