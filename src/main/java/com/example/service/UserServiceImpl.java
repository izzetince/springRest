package com.example.service;

import com.example.domain.Ticket;
import com.example.domain.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by izzetince on 30/10/2016.
 */

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public List<String> getUsernames() {
        List<String> usernames = new ArrayList<String>();
        Iterator iterator = getUsers().iterator();

        while (iterator.hasNext()) {
            User user = (User) iterator.next();
            usernames.add(user.getUsername());
        }

        return usernames;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findOne(id);
    }

    public Map<String, List<Ticket>> numberOfItemsByType(long userId) {
        Map<String, List<Ticket>> map = new HashMap<String, List<Ticket>>();
        Set<Ticket> tickets = getUserById(userId).getTickets();

        for (Ticket item: tickets) {
            List<Ticket> ticketList = new ArrayList<Ticket>();
            String key = item.getType().toLowerCase();

            if (map.containsKey(key))
                ticketList = map.get(key);

            ticketList.add(item);
            map.put(key, ticketList);
        }

        return map;
    }
}