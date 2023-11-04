package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.entity.*;
import com.project.hotelreservation.service.BookingService;
import com.project.hotelreservation.service.PaymentRequest;
import com.project.hotelreservation.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final BookingService bookingService;

    @GetMapping("/payment")
    public String showPaymentPage() {
        // Perform any necessary logic or setup
        return "customer/payment"; // This should correspond to the Thymeleaf template name
    }
    @PostMapping("/make-payment")
    public String makePayment(@ModelAttribute PaymentRequest paymentRequest, HttpSession session) {
        // Fetch necessary data from the session or other sources
        Room room = (Room) session.getAttribute("room");
        Customer customer = (Customer) session.getAttribute("customer");
        List<AdditionalServices> selectedServices = (List<AdditionalServices>) session.getAttribute("selectedServices");
        Date checkInDate = (Date) session.getAttribute("checkInDate");
        Date checkOutDate = (Date) session.getAttribute("checkOutDate");

        // Make a payment using the returned booking object
        Payment payment = paymentService.makePayment(paymentRequest.getAmount(), paymentRequest.getPaymentMethod());

        if (payment == null) {
            // Handle payment failure (e.g., invalid booking)
            return "redirect:/payment-failure";
        }
        return "redirect:/payment-success";
    }

    @GetMapping("/payment-success")
    public String showPaymentSuccessPage() {
        return "customer/payment-success";
    }
}




