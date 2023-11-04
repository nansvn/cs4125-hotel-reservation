package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.entity.*;
import com.project.hotelreservation.service.BookingService;
import com.project.hotelreservation.service.PaymentRequest;
import com.project.hotelreservation.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/payment")
    public String showPaymentPage() {
        return "customer/payment";
    }
    @PostMapping("/make-payment")
    public String makePayment(@ModelAttribute PaymentRequest paymentRequest, HttpSession session) {
        // Make a payment using the returned booking object
        Payment payment = paymentService.makePayment(paymentRequest.getAmount(), paymentRequest.getPaymentMethod());

        if (payment == null) {
            // Handle payment failure
            return "redirect:/payment-failure";
        }
        return "redirect:/payment-success";
    }

    @GetMapping("/payment-success")
    public String showPaymentSuccessPage() {
        return "customer/payment-success";
    }
}




