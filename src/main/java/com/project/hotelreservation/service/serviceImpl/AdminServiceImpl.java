package com.project.hotelreservation.service.serviceImpl;

import com.project.hotelreservation.model.entity.Admin;
import com.project.hotelreservation.repository.AdminRepository;
import com.project.hotelreservation.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    @Override
    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
}
