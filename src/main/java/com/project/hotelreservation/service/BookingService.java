package com.project.hotelreservation.service;

import com.project.hotelreservation.model.entity.AdditionalServices;
import com.project.hotelreservation.model.entity.Booking;
import com.project.hotelreservation.model.entity.Customer;
import com.project.hotelreservation.model.entity.Room;

import java.util.Date;
import java.util.List;

public interface BookingService {
    void save(Room room, List<AdditionalServices> services, Customer customer, Date checkInDate, Date checkOutDate);
    List<Booking> getOrdersByCustomer(Customer customer);
    void cancelOrder(Long bookingId);

}
