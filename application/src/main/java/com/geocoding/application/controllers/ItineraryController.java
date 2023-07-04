package com.geocoding.application.controllers;

import com.geocoding.dao.ItineraryDao;
import com.geocoding.model.Itinerary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping(
        path = "/api",
        headers = {"Accept=application/json"},
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ItineraryController {

    @Autowired
    private ItineraryDao itineraryDao;

    @PostMapping("/createItinerary")
    public ResponseEntity<Itinerary> createItinerary(
            @RequestBody Itinerary itinerary,
            @RequestParam("userId") int userId) {
        Itinerary response = itineraryDao.createItinerary(itinerary, userId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getItinerary")
    public ResponseEntity<Itinerary> getItinerary(@RequestParam int id) {
        Itinerary response = itineraryDao.getItinerary(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getItineraries")
    public ResponseEntity<List<Itinerary>> getItineraries() {
        List<Itinerary> response = itineraryDao.getItineraries();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/updateItinerary")
    public ResponseEntity<Itinerary> updateItinerary(
            @RequestBody Itinerary itinerary,
            @RequestParam("userId") int userId,
            @RequestParam(value = "stayId", required = false) Integer stayId) {
        Itinerary response = itineraryDao.updateItinerary(itinerary, userId, stayId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteItinerary")
    public ResponseEntity<Itinerary> deleteItinerary(@RequestBody Itinerary itinerary) {
        itineraryDao.deleteItinerary(itinerary);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
