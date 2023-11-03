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
        model.addAttribute("newBooking", new Booking());
        return "customer/booking";
    }

    // proceed to select the additional services
    @PostMapping("/proceed-booking")
    public String processBooking() {
        return "redirect:/additional-services";
    }

    // collect selected services and
    // proceed to the final confirmation page
    @PostMapping("/confirm-booking")
    public String confirmBooking(@RequestParam(required = false) List<Integer> serviceIds,
                                 Model model,
                                 HttpSession session) {
        List<AdditionalServices> selectedServices = additionalServicesService.getServicesByIds(serviceIds);
        model.addAttribute("selectedServices", selectedServices);
        session.setAttribute("selectedServices", selectedServices);
        return "customer/confirmation";
    }

    // handle the actual save booking actions
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


    // view history orders
    // unfinished
    @GetMapping("/view-orders")
    public String showOrders(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        model.addAttribute("orders",bookingService.getOrdersByCustomer(customer));
        return "customer/orders";
    }

    // cancel order
    // unfinished
    @GetMapping("/cancel-order")
    public String cancelOrders(@RequestParam Long bookingId) {
        bookingService.cancelOrder(bookingId);
        return "redirect:/view-orders";
    }
}





