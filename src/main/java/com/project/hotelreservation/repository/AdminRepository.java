package com.project.hotelreservation.repository;

import com.project.hotelreservation.model.entity.Admin;
import com.project.hotelreservation.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}
