package com.project.hotelreservation.repository;

import com.project.hotelreservation.model.entity.Admin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminRepositoryTest {

    @Mock
    private AdminRepository adminRepository;

    @Test
    public void testFindByUsername() {
        // Arrange
        String username = "testAdmin";
        Admin admin = new Admin();
        admin.setAdminName("Test Admin");
        when(adminRepository.findByUsername(username)).thenReturn(Optional.of(admin));

        // Act
        Optional<Admin> result = adminRepository.findByUsername(username);

        // Assert
        assertEquals(admin.getAdminName(), result.get().getAdminName());
    }
}
