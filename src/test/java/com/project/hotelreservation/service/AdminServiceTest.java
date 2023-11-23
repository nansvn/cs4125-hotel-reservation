package com.project.hotelreservation.service;

import com.project.hotelreservation.model.entity.Admin;
import com.project.hotelreservation.repository.AdminRepository;
import com.project.hotelreservation.service.serviceImpl.AdminServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    void testFindByUsername() {
        // Mock data
        String username = "admin";
        Admin mockAdmin = new Admin();
        mockAdmin.setUserId(1L);
        mockAdmin.setUsername(username);
        mockAdmin.setPassword("password");
        mockAdmin.setAdminName("Admin Name");

        // Mock repository behavior
        when(adminRepository.findByUsername(username)).thenReturn(Optional.of(mockAdmin));

        // Call the service method
        Optional<Admin> result = adminService.findByUsername(username);

        // Verify the result
        assertEquals(Optional.of(mockAdmin), result);

        // Verify that the repository method was called with the correct argument
        verify(adminRepository, times(1)).findByUsername(username);
    }
}
