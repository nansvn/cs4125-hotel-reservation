package com.project.hotelreservation.repository;

import com.project.hotelreservation.model.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void testFindByUsername() {
        String username = "John_Smith";
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setFirstName("John");
        customer.setLastName("Smith");

        // Mock the repository method;
        when(customerRepository.findByUsername(username))
                .thenReturn(Optional.of(customer));

        // Call the method
        Optional<Customer> result = customerRepository.findByUsername(username);

        // Verify the result
        assertEquals(Optional.of(customer), result);

        // Verify that the method was called with the correct argument
        verify(customerRepository, times(1)).findByUsername(username);
    }
}

