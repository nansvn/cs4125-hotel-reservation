package com.project.hotelreservation.service;

import com.project.hotelreservation.model.WebCustomer;
import com.project.hotelreservation.model.entity.Customer;

import java.util.Optional;

public interface CustomerService {
    Optional<Customer> findByUsername(String username);
    void save(WebCustomer webCustomer);
}
