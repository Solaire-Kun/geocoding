package com.geocoding.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    private int id;
    private String name;
    private double latitude;
    private double longitude;
}
