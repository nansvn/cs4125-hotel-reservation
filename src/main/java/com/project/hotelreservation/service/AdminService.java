package com.project.hotelreservation.service;

import com.project.hotelreservation.model.entity.Admin;

import java.util.Optional;

public interface AdminService {
    Optional<Admin> findByUsername(String username);
}
