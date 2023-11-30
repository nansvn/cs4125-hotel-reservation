package com.project.hotelreservation.model;

import com.project.hotelreservation.enums.Status;
import org.springframework.stereotype.Component;

@Component
public class BookingStateFactory {
    public BookingState getBookingState(Status status) {
        return switch (status) {
            case PENDING -> new PendingState();
            case CANCELLED -> new CancelledState();
            case COMPLETED -> new CompletedState();
            default -> throw new IllegalArgumentException("Unsupported booking status: " + status);
        };
    }
}
