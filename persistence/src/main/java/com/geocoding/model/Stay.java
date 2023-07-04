package com.geocoding.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Where(clause = "DELETED = false")
public class Stay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private Date stayDate;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;
    @OneToOne
    private WeatherCondition weatherCondition;
    private Boolean deleted = false;
}
