package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.entity.Booking;
import com.project.hotelreservation.model.entity.Payment;
import com.project.hotelreservation.repository.BookingRepository;
import com.project.hotelreservation.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class PaymentController {
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;

    @GetMapping("/payment/{bookingId}")
    public String showPaymentPage(@PathVariable Long bookingId, Model model) {
        // Retrieve the booking and associated payment
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        Payment payment = booking != null ? paymentRepository.findByBooking(booking) : null;

        // Add the booking and payment to the model
        model.addAttribute("booking", booking);
        model.addAttribute("payment", payment);

        return "customer/payment";
    }

}


