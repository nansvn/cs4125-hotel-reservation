package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.entity.*;
import com.project.hotelreservation.service.AdditionalServicesService;
import com.project.hotelreservation.service.BookingService;
import com.project.hotelreservation.service.PaymentService;
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
    private final PaymentService paymentService;
    private final AdditionalServicesService additionalServicesService;

    @GetMapping("/booking")
    public String showBookingPage(Model model) {
        // create a new Booking and set the initial state
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
                                 HttpSession session, Model model) {
        Room room = (Room) session.getAttribute("room");
        Customer customer = (Customer) session.getAttribute("customer");
        Date checkInDate = (Date) session.getAttribute("checkInDate");
        Date checkOutDate = (Date) session.getAttribute("checkOutDate");
        Booking booking;
        if (serviceIds != null) {
            List<AdditionalServices> selectedServices = additionalServicesService.getServicesByIds(serviceIds);
            booking = bookingService.save(room, selectedServices, customer, checkInDate, checkOutDate, false);
        } else {
            booking = bookingService.save(room, null, customer, checkInDate, checkOutDate, false);
        }
        // initialize payment info
        paymentService.initial(booking);

        if (booking != null) {
            // save for display
            model.addAttribute("booking", booking);
            session.setAttribute("booking", booking);
            session.setAttribute("payment", booking.getPayment());
        }

        return "customer/booking-confirmation";
    }


    // view history orders
    @GetMapping("/view-orders")
    public String viewOrders(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            List<Booking> userBookings = bookingService.getOrdersByCustomer(customer);
            model.addAttribute("userBookings", userBookings);
            return "customer/orders";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * @author Luxin
     */
    // cancel order
    @GetMapping("/cancel-order")
    public String cancelOrders(@RequestParam Long bookingId) {
        bookingService.cancelOrder(bookingId);
        return "redirect:/view-orders";
    }
}
