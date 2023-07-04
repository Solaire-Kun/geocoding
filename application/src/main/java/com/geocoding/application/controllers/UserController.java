package com.geocoding.application.controllers;

import com.geocoding.dao.UserDao;
import com.geocoding.model.User;
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
public class UserController {

    @Autowired
    private UserDao userDao;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User response = userDao.createUser(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestParam int id) {
        User response = userDao.getUser(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> response = userDao.getUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User response = userDao.updateUser(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        userDao.deleteUser(user);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
