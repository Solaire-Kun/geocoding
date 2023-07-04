package com.geocoding.application.controllers;

import com.geocoding.dao.StayDao;
import com.geocoding.model.Stay;
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
public class StayController {

    @Autowired
    private StayDao stayDao;

    @PostMapping("/createStay")
    public ResponseEntity<Stay> createStay(
            @RequestBody Stay stay,
            @RequestParam("itineraryId") int itineraryId) {
        Stay response = stayDao.createStay(stay, itineraryId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getStay")
    public ResponseEntity<Stay> getStay(@RequestParam int id) {
        Stay response = stayDao.getStay(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getStays")
    public ResponseEntity<List<Stay>> getStays() {
        List<Stay> response = stayDao.getStays();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/updateStay")
    public ResponseEntity<Stay> updateStay(@RequestBody Stay stay) {
        Stay response = stayDao.updateStay(stay);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteStay")
    public ResponseEntity<Stay> deleteStay(@RequestBody Stay stay) {
        stayDao.deleteStay(stay);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
