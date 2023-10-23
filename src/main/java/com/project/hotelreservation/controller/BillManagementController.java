package com.project.hotelreservation.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BillManagementController {

    @GetMapping("/billmanagement")
    public String showBillManagementPage() {
        return "billmanagement"; // Return the name of your bill management HTML template
    }
}
