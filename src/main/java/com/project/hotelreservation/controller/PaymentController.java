package com.project.hotelreservation.controller;

import com.project.hotelreservation.enums.PaymentMethod;
import com.project.hotelreservation.model.entity.*;
import com.project.hotelreservation.service.BookingService;
import com.project.hotelreservation.service.PaymentRequest;
import com.project.hotelreservation.service.PaymentService;
import com.project.hotelreservation.service.serviceImpl.BookingServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Neema
 */
@Controller
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final BookingServiceImpl bookingService;

    @GetMapping("/payment")
    public String showPaymentPage(HttpSession session, Model model) {
        Booking booking = (Booking) session.getAttribute("booking");
        model.addAttribute("booking", booking);
        return "customer/payment";
    }


    @PostMapping("/make-payment")
    public String makePayment(@RequestParam("paymentMethod") String paymentMethod,
                              @RequestParam(value = "hasMealDeal", defaultValue = "false") boolean hasMealDeal,
                              @RequestParam(value = "useRewardPoints", defaultValue = "false") boolean useRewardPoints,
                              HttpSession session) {
        Payment payment = (Payment) session.getAttribute("payment");
        Booking booking = (Booking) session.getAttribute("booking");

        // Make a payment using the returned booking object
        paymentService.makePayment(payment, PaymentMethod.valueOf(paymentMethod), hasMealDeal, useRewardPoints);

        // Update the booking status to Complete by marking paymentCompleted as true
        bookingService.save(booking.getRoom(), booking.getAdditionalServices(), booking.getCustomer(), booking.getCheckInDate(), booking.getCheckOutDate(), true);

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

    @GetMapping("/payment-failure")
    public String showPaymentFailure() {
        return "not-finshied-yet!";
    }
}