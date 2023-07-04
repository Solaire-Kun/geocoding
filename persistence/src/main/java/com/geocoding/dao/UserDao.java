package com.geocoding.dao;

import com.geocoding.model.User;

import java.util.List;

public interface UserDao {

    User createUser(User user);
    User getUser(int id);
    List<User> getUsers();
    User updateUser(User user);
    String deleteUser(User user);
}
