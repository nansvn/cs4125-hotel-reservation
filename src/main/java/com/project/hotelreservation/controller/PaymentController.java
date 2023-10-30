package com.project.hotelreservation.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {

    @GetMapping("/payment")
    public String showBillManagementPage() {
        return "payment"; // Return the name of your bill management HTML template
    }
}
