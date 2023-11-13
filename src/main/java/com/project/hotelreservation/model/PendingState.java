package com.project.hotelreservation.model;


import com.project.hotelreservation.enums.BookingStatus;
import com.project.hotelreservation.model.entity.Booking;

public class PendingState implements BookingState {

    @Override
    public void handle(Booking booking) {
        //handle operation of pending state
    }

    @Override
    public BookingStatus getStatus() {
        return BookingStatus.PENDING;
    }
}