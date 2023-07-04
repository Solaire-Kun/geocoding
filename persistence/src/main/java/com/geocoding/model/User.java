package com.geocoding.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Where(clause = "DELETED = false")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    @Column(unique = true)
    private String email;
    private String name;
    private String surname;
    @ManyToOne
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;
    private Boolean deleted = false;
}
