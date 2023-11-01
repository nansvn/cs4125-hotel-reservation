package com.project.hotelreservation.service;

import com.project.hotelreservation.model.entity.Booking;

public interface BookingState {
    void checkIn(Booking booking);
    void checkOut(Booking booking);
    void cancel(Booking booking);
}
