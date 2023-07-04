package com.geocoding.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Where(clause = "DELETED = false")
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private Date startDate;
    private Date endDate;
    @OneToMany(mappedBy = "itinerary")
    @JsonIgnore
    private List<User> users = new ArrayList<>();
    @OneToMany(mappedBy = "itinerary")
    @JsonIgnore
    private List<Stay> stays = new ArrayList<>();
    private String state;
    private Boolean deleted = false;

    public void addUser(User user) {
        if (user != null) {
            users.add(user);
        }
    }
}
