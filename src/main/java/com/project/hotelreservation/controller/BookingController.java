package com.project.hotelreservation.controller;
import com.project.hotelreservation.model.entity.Booking;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingController {

    @GetMapping("/booking")
    public String showBookingPage(Model model) {

        model.addAttribute("newBooking", new Booking());
        return "booking";
    }

    @GetMapping("/viewOrders")
    public String showOrders(Model model) {
        return "orders";
    }
}
