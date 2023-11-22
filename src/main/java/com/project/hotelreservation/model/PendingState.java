package com.project.hotelreservation.model;


import com.project.hotelreservation.enums.BookingStatus;
import com.project.hotelreservation.model.entity.Booking;
public class PendingState implements BookingState {

    @Override
    public void handle(Booking booking) {
        System.out.println("Waiting for payment to proceed....");
    }

    @Override
    public BookingStatus getStatus() {
        return BookingStatus.PENDING;
    }
}