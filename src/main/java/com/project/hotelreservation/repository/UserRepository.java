package com.project.hotelreservation.repository;

import com.project.hotelreservation.entity.Customer;
import com.project.hotelreservation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<Customer> findByUsername(String username);
}
