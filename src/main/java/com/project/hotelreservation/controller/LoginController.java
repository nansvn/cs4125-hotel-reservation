package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.entity.Admin;
import com.project.hotelreservation.model.entity.Customer;
import com.project.hotelreservation.service.AdminService;
import com.project.hotelreservation.service.CustomerService;
import jakarta.servlet.http.HttpSession;
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
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        Optional<Customer> customer = customerService.findByUsername(username);
        Optional<Admin> admin = adminService.findByUsername(username);

        if (customer.isPresent() && customer.get().getPassword().equals(password)) {
            session.setAttribute("customer", customer.get());
            return "redirect:/home";
        } else if (admin.isPresent() && admin.get().getPassword().equals(password)) {
            session.setAttribute("admin", admin.get());
            return "redirect:/home";
        } else {
            model.addAttribute("invalidMessage", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        model.addAttribute("logoutMessage", "Logout successfully");
        session.invalidate();
        return "login";
    }
}

