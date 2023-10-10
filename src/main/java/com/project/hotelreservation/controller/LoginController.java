package com.project.hotelreservation.controller;

import com.project.hotelreservation.entity.Customer;
import com.project.hotelreservation.entity.User;
import com.project.hotelreservation.service.UserService;
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
    private final UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "index";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {
        Optional<User> user = userService.findUserByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            if (user.get().getRole().equals("admin")) {
                return "redirect:/admin";
            } else {
                return "redirect:/homepage";
            }
        } else {
            model.addAttribute("message", "Invalid username or password");
            return "index";
        }
    }

}

