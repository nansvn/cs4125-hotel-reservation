package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.entity.AdditionalServices;
import com.project.hotelreservation.service.AdditionalServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdditionalServicesController {
    @Autowired
    private AdditionalServicesService additionalServicesService;

    @GetMapping("/additionalservices")
    public String viewAdditionalServicesPage(Model model) {
        model.addAttribute("additionalServices", additionalServicesService.getAllAdditionalServices());
        return "additionalservices"; // Replace with the name of your HTML template file
    }
}

