package com.project.hotelreservation.controller;

import com.project.hotelreservation.utils.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/home")
    public String showHome(Model model) {
        if (WebUtils.getSession().getAttribute("customer") == null) {
            model.addAttribute("accessDeniedMessage", "Please log in first");
            return "login";
        }
        return "home";
    }
}
