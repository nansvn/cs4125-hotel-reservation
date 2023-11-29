package com.project.hotelreservation.repository;

import com.project.hotelreservation.model.WebCustomer;
import com.project.hotelreservation.model.entity.Customer;
import com.project.hotelreservation.service.serviceImpl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerRepositoryTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void testFindByUsername() {
        // Arrange
        String username = "JohnSmith";
        Customer customer = new Customer();
        customer.setUsername(username);

        when(customerRepository.findByUsername(username)).thenReturn(Optional.of(customer));

        // Act
        Optional<Customer> result = customerService.findByUsername(username);

        // Assert
        assertEquals(username, result.orElseThrow().getUsername());
        verify(customerRepository, times(1)).findByUsername(username);
    }

    @Test
    public void testSave() {
        // Arrange
        WebCustomer webCustomer = new WebCustomer();
        webCustomer.setUsername("JOHN123");
        webCustomer.setPassword("123");
        webCustomer.setFirstName("JOHN");
        webCustomer.setLastName("Smith");
        webCustomer.setEmail("JohnSmitch@gmail.com");

        // Act
        customerService.save(webCustomer);

        // Assert
        verify(customerRepository, times(1)).save(any(Customer.class));
    }
}
