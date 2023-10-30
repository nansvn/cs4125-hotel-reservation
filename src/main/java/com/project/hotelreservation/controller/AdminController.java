package com.project.hotelreservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.project.hotelreservation.model.entity.Admin;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        // Create an example admin object with adminName
        Admin admin = new Admin();
        admin.setAdminName("Emily C");

        // Add the admin object to the model
        model.addAttribute("admin", admin);

        return "admin";
    }
}


