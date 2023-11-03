package com.project.hotelreservation.controller;

import com.project.hotelreservation.service.AdditionalServicesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class AdditionalServicesController {
    private AdditionalServicesService additionalServicesService;

    @GetMapping("/additional-services")
    public String viewAdditionalServicesPage(Model model) {
        model.addAttribute("additionalServices", additionalServicesService.getAllAdditionalServices());
        return "customer/additional-services";
    }

}

