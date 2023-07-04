package com.geocoding.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class City {
    @Id
    private int id;
    private String name;
    private Double longitude;
    private Double latitude;
}
