package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.entity.Booking;
import com.project.hotelreservation.model.entity.Payment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.project.hotelreservation.repository.BookingRepository;
import com.project.hotelreservation.repository.PaymentRepository;



@Controller
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/booking")
    public String showBookingPage(Model model) {

        // Create a new Booking and set the initial state
        Booking booking = new Booking();

        // Handle state transitions by calling the methods directly
        booking.checkIn(); // This will transition the state to CheckedInState if valid
        booking.checkOut(); // This will transition the state to CheckedOutState
        booking.cancel(); // This will transition the state to CancelledState

        model.addAttribute("newBooking", new Booking());
        return "booking";
    }

    @PostMapping("/booking")
    public String processBooking(@ModelAttribute("newBooking") Booking newBooking)  {
        Booking savedBooking = bookingRepository.save(newBooking);

        // Create a Payment object
        Payment payment = new Payment();
        payment.setAmount(100.0); // Set the payment amount as needed
        payment.setBooking(savedBooking); // Associate the payment with the booking

        paymentRepository.save(payment);

        // Redirect to the payment page
        return "redirect:/payment/" + savedBooking.getBookingId();
    }

    @GetMapping("/viewOrders")
    public String showOrders(Model model) {
        return "orders";
    }
}




