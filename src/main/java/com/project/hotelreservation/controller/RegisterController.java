package com.project.hotelreservation.controller;


import com.project.hotelreservation.entity.Customer;
import com.project.hotelreservation.entity.User;
import com.project.hotelreservation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("customer") Customer customer, Model model) {
        String username = customer.getUsername();
        Optional<User> user = userService.findUserByUsername(username);
        if (user.isPresent()) {
            model.addAttribute("customer", new Customer());
            model.addAttribute("registerError", "Username already exists");
            return "register";
        } else {
            userService.save(customer);
            return "index";
        }
    }
}
