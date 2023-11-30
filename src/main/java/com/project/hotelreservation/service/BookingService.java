package com.project.hotelreservation.service;

import com.project.hotelreservation.model.entity.AdditionalServices;
import com.project.hotelreservation.model.entity.Booking;
import com.project.hotelreservation.model.entity.Customer;
import com.project.hotelreservation.model.entity.Room;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
public interface BookingService {
    Booking save(Room room, List<AdditionalServices> services, Customer customer, Date checkInDate, Date checkOutDate, boolean paymentCompleted);

    List<Booking> getOrdersByCustomer(Customer customer);

    void cancelOrder(Long bookingId);

    Booking getBookingById(Long bookingId);
}