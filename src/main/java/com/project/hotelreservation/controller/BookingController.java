package com.project.hotelreservation.controller;

import com.project.hotelreservation.enums.BookingStatus;
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
    @GetMapping("/proceed-booking")
    public String processBooking(Model model) {
        model.addAttribute("additionalServices", additionalServicesService.getAllAdditionalServices());
        return "customer/additional-services";
    }


    // create the order with collected additional services info
    // display the order overview
    @PostMapping("/confirm-booking")
    public String confirmBooking(@RequestParam(required = false) List<Integer> serviceIds,
                                 HttpSession session) {
        if(serviceIds != null) {
            List<AdditionalServices> selectedServices = additionalServicesService.getServicesByIds(serviceIds);
            Room room = (Room) session.getAttribute("room");
            Customer customer = (Customer) session.getAttribute("customer");
            Date checkInDate = (Date) session.getAttribute("checkInDate");
            Date checkOutDate = (Date) session.getAttribute("checkOutDate");
            bookingService.save(room, selectedServices, customer, checkInDate, checkOutDate);
        }else{

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





