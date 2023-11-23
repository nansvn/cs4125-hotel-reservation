package com.project.hotelreservation.service;

import com.project.hotelreservation.model.entity.AdditionalServices;
import com.project.hotelreservation.repository.AdditionalServicesRepository;
import com.project.hotelreservation.service.serviceImpl.AdditionalServicesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdditionalServicesServiceImplTest {

    @Mock
    private AdditionalServicesRepository additionalServicesRepository;

    @InjectMocks
    private AdditionalServicesServiceImpl additionalServicesService;

    private List<AdditionalServices> mockServices;

    @BeforeEach
    void setUp() {
        // Mock data
        mockServices = Arrays.asList(
                new AdditionalServices(1, "Cleaning", "Cleaning service description", 10.0),
                new AdditionalServices(2, "Catering", "Catering service description", 20.0)
        );
    }

    @Test
    void testGetAllAdditionalServices() {
        // Mock repository behavior
        when(additionalServicesRepository.findAll()).thenReturn(mockServices);

        // Call the service method
        List<AdditionalServices> result = additionalServicesService.getAllAdditionalServices();

        // Verify the result
        assertEquals(mockServices, result);
    }

    @Test
    void testGetServicesByIds() {
        // Mock data
        List<Integer> serviceIds = Arrays.asList(1, 2);

        // Mock repository behavior
        when(additionalServicesRepository.findByServiceIdIn(serviceIds)).thenReturn(mockServices);

        // Call the service method
        List<AdditionalServices> result = additionalServicesService.getServicesByIds(serviceIds);

        // Verify the result
        assertEquals(mockServices, result);
    }
}
