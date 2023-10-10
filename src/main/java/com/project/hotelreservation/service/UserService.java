package com.project.hotelreservation.service;

import com.project.hotelreservation.entity.Customer;
import com.project.hotelreservation.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByUsername(String username);
    void save(Customer customer);
}
