package com.geocoding.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WeatherCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int humidity;
    private Double maxTemperature;
    private Double minTemperature;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name= "stay_id")
    private Stay stay;
    private Date stayDate;
    @Enumerated
    private SkyConditions skyCondition;

    public void setSkyConditionByCode(int weathercode) {
        switch (weathercode) {
            case 0, 1:
                this.skyCondition = SkyConditions.CLEAR_SKY;
                break;
            case 2, 3:
                this.skyCondition = SkyConditions.CLOUDY;
                break;
            case 45, 48:
                this.skyCondition = SkyConditions.FOG;
                break;
            case 51, 53, 55, 56, 57, 61, 63, 65, 66, 67, 80, 81, 82, 95, 96, 99:
                this.skyCondition = SkyConditions.RAIN_SHOWERS;
                break;
            case 71, 73, 75, 77, 85, 86:
                this.skyCondition = SkyConditions.SNOW_SHOWERS;
                break;
            default:
                this.skyCondition = SkyConditions.UNKNOWN;
                break;
        }
    }
}
