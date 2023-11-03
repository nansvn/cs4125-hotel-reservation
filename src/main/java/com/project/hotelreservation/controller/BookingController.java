package com.project.hotelreservation.controller;

import com.project.hotelreservation.enums.BookingStatus;
import com.project.hotelreservation.model.entity.Booking;
import com.project.hotelreservation.repository.RoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.project.hotelreservation.repository.BookingRepository;

@Controller
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/booking")
    public String showBookingPage(Model model) {
        model.addAttribute("newBooking", new Booking());
        // Other model data setup, if needed
        return "booking";
    }
    @PostMapping("/booking")
    public String processBooking(@ModelAttribute("newBooking") Booking newBooking)  {

        Booking savedBooking = bookingRepository.save(newBooking);

        // Redirect to the booking confirmation page
        return "redirect:/booking_confirmation/" + savedBooking.getBookingId();
    }


    @GetMapping("/viewOrders")
    public String showOrders(Model model) {
        return "orders";
    }
    @GetMapping("/booking_confirmation/{bookingId}")
    public String showBookingConfirmationPage(@PathVariable Long bookingId, Model model) {
        // Retrieve booking details based on the bookingId
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        booking.setStatus(BookingStatus.COMPLETED);
        // Pass booking details to the confirmation page
        model.addAttribute("booking", booking);

        return "booking_confirmation";
    }

    // ... other methods ...
}





