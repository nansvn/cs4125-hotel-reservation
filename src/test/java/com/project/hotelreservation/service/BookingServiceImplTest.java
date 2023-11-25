package com.project.hotelreservation.service;

import com.project.hotelreservation.model.BookingState;
import com.project.hotelreservation.model.BookingStateFactory;
import com.project.hotelreservation.model.entity.*;
import com.project.hotelreservation.repository.BookingRepository;
import com.project.hotelreservation.repository.RoomRepository;
import com.project.hotelreservation.service.BookingService;
import com.project.hotelreservation.service.serviceImpl.BookingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private BookingStateFactory bookingStateFactory;

    @Test
    public void testSaveBooking() {
        Room room = new Room();
        Customer customer = new Customer();
        List<AdditionalServices> services = new ArrayList<>();
        Date checkInDate = new Date();
        Date checkOutDate = new Date();
        boolean paymentCompleted = true;

        BookingState bookingState = mock(BookingState.class);
        when(bookingStateFactory.getBookingState(any())).thenReturn(bookingState);

        bookingService.save(room, services, customer, checkInDate, checkOutDate, paymentCompleted);

        verify(bookingStateFactory).getBookingState(any());
        verify(bookingRepository).save(any(Booking.class));
        verify(roomRepository).save(any(Room.class));

    }

    @Test
    public void testGetOrdersByCustomer() {
        Customer customer = new Customer();
        List<Booking> bookings = Arrays.asList(new Booking(), new Booking());

        when(bookingRepository.findBookingByCustomer(any())).thenReturn(bookings);

        List<Booking> result = bookingService.getOrdersByCustomer(customer);

        verify(bookingRepository).findBookingByCustomer(any());
        assertEquals(bookings.size(), result.size());

    }

    @Test
    public void testCancelOrder() {
    }
}

