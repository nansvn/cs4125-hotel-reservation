package com.project.hotelreservation.service;

import com.project.hotelreservation.entity.Customer;

import java.util.Optional;

public interface UserService {
    Optional<Customer> findUserByUsername(String username);
}
