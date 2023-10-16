package com.project.hotelreservation.controller;


import com.project.hotelreservation.model.WebCustomer;
import com.project.hotelreservation.model.entity.Customer;
import com.project.hotelreservation.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class RegisterController {
    private final CustomerService customerService;
    private final Logger logger = Logger.getLogger(getClass().getName());

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("webCustomer", new WebCustomer());
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid
            @ModelAttribute("webCustomer") WebCustomer webCustomer,
            Model model) {
        String username = webCustomer.getUsername();
        Optional<Customer> user = customerService.findByUsername(username);

        if (user.isPresent()) {
            model.addAttribute("webCustomer", new WebCustomer());
            model.addAttribute("existMessage", "User already exists");
            logger.warning("User already exists.");
            return "register";
        }

        model.addAttribute("successMessage", "Registration successful. Please login.");
        customerService.save(webCustomer);

        logger.info("Successfully created customer: " + username);

        return "index";
    }
}

