package com.project.hotelreservation.model;

import com.project.hotelreservation.enums.BookingStatus;
import com.project.hotelreservation.model.entity.Booking;

public class CompletedState implements BookingState {

    @Override
    public void handle(Booking booking) {
        // Handle operations for COMPLETED state
    }

    @Override
    public BookingStatus getStatus() {
        return BookingStatus.COMPLETED;
    }
}