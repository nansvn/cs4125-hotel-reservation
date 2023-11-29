package com.project.hotelreservation.model;

import com.project.hotelreservation.enums.Status;
import com.project.hotelreservation.model.entity.Booking;

public class CancelledState implements BookingState {
    @Override
    public void handle(Booking booking) {
        // Handle operations for CANCELLED state
        processRefund(booking);

    }

    private void processRefund(Booking booking) {
        System.out.println("Refund is made....");
    }


    @Override
    public Status getStatus() {
        return Status.CANCELLED;
    }
}