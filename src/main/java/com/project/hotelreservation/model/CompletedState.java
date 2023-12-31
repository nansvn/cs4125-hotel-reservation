package com.project.hotelreservation.model;

import com.project.hotelreservation.enums.Status;
import com.project.hotelreservation.model.entity.Booking;

public class CompletedState implements BookingState {

    @Override
    public void handle(Booking booking) {
        // Handle operations for COMPLETED state
        System.out.println("Completed reservation email is sent...");
    }

    @Override
    public Status getStatus() {
        return Status.COMPLETED;
    }
}