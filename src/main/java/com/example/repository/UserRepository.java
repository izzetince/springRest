package com.example.repository;

import com.example.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by izzetince on 30/10/2016.
 */

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
