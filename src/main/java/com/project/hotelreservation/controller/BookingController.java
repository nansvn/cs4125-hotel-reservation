package com.project.hotelreservation.controller;
import com.project.hotelreservation.model.entity.Booking;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingController {

    @GetMapping("/booking")
    public String showBookingPage(Model model) {
        // You can add data to the model to populate your Thymeleaf template
        model.addAttribute("newBooking", new Booking());
        return "booking"; // This maps to the "make-order.html" template in the "templates" directory
    }

    @GetMapping("/viewOrders")
    public String showOrders(Model model) {
        return "orders";
    }
}
