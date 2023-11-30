package com.project.hotelreservation.service.serviceImpl;

import com.project.hotelreservation.enums.Status;
import com.project.hotelreservation.model.BookingState;
import com.project.hotelreservation.model.entity.*;
import com.project.hotelreservation.repository.BookingRepository;
import com.project.hotelreservation.repository.RoomRepository;
import com.project.hotelreservation.service.BookingService;
import com.project.hotelreservation.model.BookingStateFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final BookingStateFactory bookingStateFactory;

    @Override
    public Booking save(Room room, List<AdditionalServices> services, Customer customer, Date checkInDate, Date checkOutDate, boolean paymentCompleted) {
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setCustomer(customer);
        booking.setAdditionalServices(services);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setBookingDate(Timestamp.from(Instant.now()));
        BookingState bookingState;

        if (paymentCompleted) {
            booking.complete();
            bookingState = bookingStateFactory.getBookingState(Status.COMPLETED);
        } else {
            booking.process();
            bookingState = bookingStateFactory.getBookingState(Status.PENDING);
        }
        booking.transitionToState(bookingState);
        bookingRepository.save(booking);
        room.setAvailable(false);
        roomRepository.save(room);
        return booking;
    }


    @Override
    public List<Booking> getOrdersByCustomer(Customer customer) {
        return bookingRepository.findBookingByCustomer(customer);
    }

    @Transactional
    @Override
    public void cancelOrder(Long bookingId) {
        Optional<Booking> optional = bookingRepository.findById(bookingId);
        Booking booking = null;

        // we get the booking from optional
        if (optional.isPresent()) {
            booking = optional.get();
            booking.cancel();
            BookingState bookingState = bookingStateFactory.getBookingState(Status.CANCELLED);
            booking.transitionToState(bookingState);
            bookingRepository.save(booking);

            Room room = booking.getRoom();
            room.setAvailable(true);
            roomRepository.save(room);

            bookingRepository.deleteBookingByBookingId(bookingId);

        } else {
            throw new RuntimeException("Booking not found for id ::" + bookingId);
        }

    }

    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found for id: " + bookingId));
    }

}