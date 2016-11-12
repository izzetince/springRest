package com.example.service;

import com.example.domain.Ticket;
import com.example.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by izzetince on 30/10/2016.
 */

public interface UserService {

    void addUser(User user);

    Iterable<User> getUsers();

    List<String> getUsernames();

    User getUserByUsername(String username);

    User getUserById(long id);

    Map<String, List<Ticket>> numberOfItemsByType(long userId);
}
