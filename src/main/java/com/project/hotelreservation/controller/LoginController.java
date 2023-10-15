package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.entity.Admin;
import com.project.hotelreservation.model.entity.Customer;
import com.project.hotelreservation.service.AdminService;
import com.project.hotelreservation.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final CustomerService customerService;
    private final AdminService adminService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "index";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {

        Optional<Customer> customer = customerService.findByUsername(username);
        Optional<Admin> admin = adminService.findByUsername(username);

        if (customer.isPresent() && customer.get().getPassword().equals(password)) {
            return "homepage";
        } else if (admin.isPresent() && admin.get().getPassword().equals(password)) {
            return "admin";
        } else {
            model.addAttribute("invalidMessage", "Invalid username or password");
            return "index";
        }
    }
}

