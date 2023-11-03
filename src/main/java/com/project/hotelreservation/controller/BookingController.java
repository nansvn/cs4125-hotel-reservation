package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.entity.AdditionalServices;
import com.project.hotelreservation.model.entity.Booking;
import com.project.hotelreservation.model.entity.Customer;
import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.service.AdditionalServicesService;
import com.project.hotelreservation.service.BookingService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;


@Controller
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final AdditionalServicesService additionalServicesService;

    @GetMapping("/booking")
    public String showBookingPage(Model model) {
        // Create a new Booking and set the initial state
        Booking booking = new Booking();

        // Handle state transitions by calling the methods directly
        booking.checkIn(); // This will transition the state to CheckedInState if valid
        booking.checkOut(); // This will transition the state to CheckedOutState
        booking.cancel(); // This will transition the state to CancelledState

        model.addAttribute("newBooking", new Booking());
        return "customer/booking";
    }

    @PostMapping("/proceed-booking")
    public String processBooking() {
        // proceed to the additional services page
        return "redirect:/additional-services";
    }

    @PostMapping("/confirm-booking")
    public String confirmBooking(@RequestParam(required = false) List<Integer> serviceIds,
                                 Model model,
                                 HttpSession session) {
        List<AdditionalServices> selectedServices = additionalServicesService.getServicesByIds(serviceIds);
        model.addAttribute("selectedServices",selectedServices);
        session.setAttribute("selectedServices",selectedServices);
        return "customer/confirmation";
    }

    @PostMapping("/save-booking")
    public String saveBooking(HttpSession session) {
        Room room = (Room) session.getAttribute("room");
        Customer customer = (Customer) session.getAttribute("customer");
        @SuppressWarnings("unchecked")
        List<AdditionalServices> selectedServices = (List<AdditionalServices>) session.getAttribute("selectedServices");
        Date checkInDate = (Date) session.getAttribute("checkInDate");
        Date checkOutDate = (Date) session.getAttribute("checkOutDate");
        bookingService.save(room, selectedServices, customer, checkInDate, checkOutDate);
        // proceed to the payment page
        return "redirect:/payment";
    }

    @GetMapping("/viewOrders")
    public String showOrders(Model model) {
        return "customer/orders";
    }
}




