package com.project.hotelreservation.controller;

import com.project.hotelreservation.entity.Customer;
import com.project.hotelreservation.entity.User;
import com.project.hotelreservation.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping("/login")
    public String Login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {
        Optional<Customer> customer = userService.findUserByUsername(username);
        if (customer.isPresent() && customer.get().getPassword().equals(password)) {
            return "redirect:/homepage";
        } else {
            model.addAttribute("message", "Invalid username or password");
            return "index";
        }
    }
}

