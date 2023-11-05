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

/**
 * @author Luxin, Neema, Nan
 */
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

    /**
     * @author Nan
     */
    // after customer select the room they want to book, they will be here
    // show a list of additional services for selection
    @GetMapping("/proceed-booking")
    public String processBooking(Model model) {

        model.addAttribute("additionalServices", additionalServicesService.getAllAdditionalServices());
        return "customer/additional-services";
    }

    /**
     * @author Nan
     */
    // after customer select the additional services they will be here
    // order created with collected additional services info
    // display the order overview
    @PostMapping("/confirm-booking")
    public String confirmBooking(@RequestParam(required = false) List<Integer> serviceIds,
                                 HttpSession session) {
        Room room = (Room) session.getAttribute("room");
        Customer customer = (Customer) session.getAttribute("customer");
        Date checkInDate = (Date) session.getAttribute("checkInDate");
        Date checkOutDate = (Date) session.getAttribute("checkOutDate");
        if (serviceIds != null) {
            List<AdditionalServices> selectedServices = additionalServicesService.getServicesByIds(serviceIds);
            bookingService.save(room, selectedServices, customer, checkInDate, checkOutDate);
        } else {
            bookingService.save(room, null, customer, checkInDate, checkOutDate);
        }

        return "customer/booking-confirmation";
    }

    // view history orders
    // unfinished
    @GetMapping("/view-orders")
    public String showOrders(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        model.addAttribute("orders", bookingService.getOrdersByCustomer(customer));
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
