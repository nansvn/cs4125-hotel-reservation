package com.project.hotelreservation.service;

import com.project.hotelreservation.model.WebCustomer;
import com.project.hotelreservation.model.entity.Customer;
import com.project.hotelreservation.repository.CustomerRepository;
import com.project.hotelreservation.service.CustomerService;
import com.project.hotelreservation.service.serviceImpl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer mockCustomer;

    @BeforeEach
    void setUp() {
        // Initialize common objects or perform setup tasks here
        mockCustomer = new Customer();
        mockCustomer.setUserId(1L);
        mockCustomer.setUsername("John111");
        mockCustomer.setPassword("password");
        mockCustomer.setFirstName("John");
        mockCustomer.setLastName("Smith");
        mockCustomer.setEmail("john.Smith@gmail.com");
        mockCustomer.setRewardPoints(0);
    }

    @Test
    void testFindByUsername() {
        // Mock repository behavior
        when(customerRepository.findByUsername("John111")).thenReturn(Optional.of(mockCustomer));

        // Call the service method
        Optional<Customer> result = customerService.findByUsername("John111");

        // Verify the result
        assertEquals(Optional.of(mockCustomer), result);

        // Verify that the repository method was called with the correct argument
        verify(customerRepository, times(1)).findByUsername("John111");
    }

    @Test
    void testSave() {
        // Mock data
        WebCustomer webCustomer = new WebCustomer();
        webCustomer.setUsername("User1");
        webCustomer.setPassword("newPassword");
        webCustomer.setFirstName("User1");
        webCustomer.setLastName("User");
        webCustomer.setEmail("User1user@gmail.com");

        // Call the service method
        customerService.save(webCustomer);

        // Verify that the repository save method was called with the correct argument
        verify(customerRepository, times(1)).save(argThat(customer ->
                "User1".equals(customer.getUsername())
                        && "newPassword".equals(customer.getPassword())
                        && "User1".equals(customer.getFirstName())
                        && "User".equals(customer.getLastName())
                        && "User1user@gmail.com".equals(customer.getEmail())
                        && customer.getRewardPoints() == 0
        ));
    }
}
