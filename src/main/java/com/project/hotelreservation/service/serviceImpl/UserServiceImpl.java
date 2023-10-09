package com.project.hotelreservation.service.serviceImpl;

import com.project.hotelreservation.entity.Customer;
import com.project.hotelreservation.entity.User;
import com.project.hotelreservation.repository.UserRepository;
import com.project.hotelreservation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<Customer> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

