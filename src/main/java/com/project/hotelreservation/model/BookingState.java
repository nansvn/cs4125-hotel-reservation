package com.project.hotelreservation.model;

import com.project.hotelreservation.enums.BookingStatus;
import com.project.hotelreservation.model.entity.Booking;

public interface BookingState {
    void handle(Booking booking);
    BookingStatus getStatus();
}
