package com.geocoding.dao;

import com.geocoding.model.Itinerary;

import java.util.List;

public interface ItineraryDao {

    Itinerary createItinerary(Itinerary itinerary, int userId);
    Itinerary getItinerary(int id);
    List<Itinerary> getItineraries();
    Itinerary updateItinerary(Itinerary itinerary, int userId, int stayId);
    String deleteItinerary(Itinerary itinerary);
}
