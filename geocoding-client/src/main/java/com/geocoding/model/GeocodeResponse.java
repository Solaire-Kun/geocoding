package com.geocoding.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeResponse {
    private List<Result> results;
    private double generationtime_ms;
}
