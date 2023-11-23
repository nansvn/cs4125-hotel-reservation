package com.project.hotelreservation.model;

import com.project.hotelreservation.enums.BookingStatus;
import com.project.hotelreservation.model.BookingState;
import com.project.hotelreservation.model.CancelledState;
import com.project.hotelreservation.model.CompletedState;
import com.project.hotelreservation.model.PendingState;
import org.springframework.stereotype.Component;

@Component
public class BookingStateFactory {
    public BookingState getBookingState(BookingStatus status) {
        return switch (status) {
            case PENDING -> new PendingState();
            case CANCELLED -> new CancelledState();
            case COMPLETED -> new CompletedState();
            default -> throw new IllegalArgumentException("Unsupported booking status: " + status);
        };
    }
}
