package com.project.hotelreservation.service.serviceImpl;

import com.project.hotelreservation.model.WebCustomer;
import com.project.hotelreservation.model.entity.Customer;
import com.project.hotelreservation.repository.CustomerRepository;
import com.project.hotelreservation.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public void save(WebCustomer webCustomer) {
        Customer customer = new Customer();
        customer.setUsername(webCustomer.getUsername());
        customer.setPassword(webCustomer.getPassword());
        customer.setFirstName(webCustomer.getFirstName());
        customer.setLastName(webCustomer.getLastName());
        customer.setEmail(webCustomer.getEmail());
        customer.setRewardPoints(0);
        customerRepository.save(customer);
    }
}
