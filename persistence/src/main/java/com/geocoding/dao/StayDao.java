package com.geocoding.dao;

import com.geocoding.model.Stay;

import java.util.List;

public interface StayDao {

    Stay createStay(Stay stay, int itineraryId);
    Stay getStay(int id);
    List<Stay> getStays();
    Stay updateStay(Stay stay);
    String deleteStay(Stay stay);
}
