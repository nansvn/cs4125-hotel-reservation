package com.project.hotelreservation.model;

import com.project.hotelreservation.enums.BookingStatus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class BookingStateFactoryTest {

    @Test
    void testGetBookingStatePending() {
        BookingStateFactory factory = new BookingStateFactory();
        BookingState pendingState = factory.getBookingState(BookingStatus.PENDING);

        assertEquals(PendingState.class, pendingState.getClass());
    }

    @Test
    void testGetBookingStateCancelled() {
        BookingStateFactory factory = new BookingStateFactory();
        BookingState cancelledState = factory.getBookingState(BookingStatus.CANCELLED);

        assertEquals(CancelledState.class, cancelledState.getClass());
    }

    @Test
    void testGetBookingStateCompleted() {
        BookingStateFactory factory = new BookingStateFactory();
        BookingState completedState = factory.getBookingState(BookingStatus.COMPLETED);

        assertEquals(CompletedState.class, completedState.getClass());
    }

}
