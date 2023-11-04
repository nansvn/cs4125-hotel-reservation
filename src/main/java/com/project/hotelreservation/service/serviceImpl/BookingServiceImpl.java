package com.project.hotelreservation.service.serviceImpl;

import com.project.hotelreservation.enums.BookingStatus;
import com.project.hotelreservation.model.entity.AdditionalServices;
import com.project.hotelreservation.model.entity.Booking;
import com.project.hotelreservation.model.entity.Customer;
import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.repository.BookingRepository;
import com.project.hotelreservation.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    @Override
    public Booking save(Room room, List<AdditionalServices> services, Customer customer, Date checkInDate, Date checkOutDate) {
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setCustomer(customer);
        booking.setAdditionalServices(services);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setPayment(null);
        booking.setBookingDate(Timestamp.from(Instant.now()));
        booking.setStatus(BookingStatus.PENDING);
        bookingRepository.save(booking);
        return booking;
    }

    @Override
    public List<Booking> getOrdersByCustomer(Customer customer) {
        return bookingRepository.findBookingByCustomer(customer);
    }

    @Override
    public void cancelOrder(Long bookingId) {
        bookingRepository.deleteBookingByBookingId(bookingId);
    }
}


