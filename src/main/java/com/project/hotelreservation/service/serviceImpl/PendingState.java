package com.project.hotelreservation.service.serviceImpl;

import com.project.hotelreservation.model.entity.Booking;
import com.project.hotelreservation.service.BookingState;

public class PendingState implements BookingState {
    @Override
    public void checkIn(Booking booking) {
        // Handle check-in logic for the pending booking
        // Transition to CheckedInState if valid
    }

    @Override
    public void checkOut(Booking booking) {
        // Handle check-out logic for the pending booking
    }

    @Override
    public void cancel(Booking booking) {
        // Handle cancellation logic for the pending booking
        // Transition to CancelledState
    }
}
