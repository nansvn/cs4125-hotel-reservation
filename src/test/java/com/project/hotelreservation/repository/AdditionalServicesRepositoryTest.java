package com.project.hotelreservation.repository;

import com.project.hotelreservation.model.entity.AdditionalServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdditionalServicesRepositoryTest {

    @Mock
    private AdditionalServicesRepository additionalServicesRepository;

    @Test
    public void testFindByServiceIdIn() {
        // Arrange
        List<Integer> ids = Arrays.asList(1, 2, 3);
        AdditionalServices service1 = new AdditionalServices();
        service1.setServiceId(1);
        service1.setName("Cleaning");
        AdditionalServices service2 = new AdditionalServices();
        service2.setServiceId(2);
        service2.setName("Catering");
        when(additionalServicesRepository.findByServiceIdIn(ids)).thenReturn(Arrays.asList(service1, service2));

        // Act
        List<AdditionalServices> result = additionalServicesRepository.findByServiceIdIn(ids);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Cleaning", result.get(0).getName());
        assertEquals("Catering", result.get(1).getName());
    }
}
