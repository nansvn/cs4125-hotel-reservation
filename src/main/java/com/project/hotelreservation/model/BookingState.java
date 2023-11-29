package com.project.hotelreservation.model;

import com.project.hotelreservation.enums.Status;
import com.project.hotelreservation.model.entity.Booking;

public interface BookingState {
    void handle(Booking booking);
    Status getStatus();
}
