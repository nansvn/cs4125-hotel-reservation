package com.project.hotelreservation.service.serviceImpl;

import com.project.hotelreservation.enums.BookingStatus;
import com.project.hotelreservation.model.BookingState;
import com.project.hotelreservation.model.CancelledState;
import com.project.hotelreservation.model.CompletedState;
import com.project.hotelreservation.model.PendingState;
import com.project.hotelreservation.model.entity.AdditionalServices;
import com.project.hotelreservation.model.entity.Booking;
import com.project.hotelreservation.model.entity.Customer;
import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.repository.BookingRepository;
import com.project.hotelreservation.repository.RoomRepository;
import com.project.hotelreservation.service.BookingService;
import com.project.hotelreservation.service.BookingStateFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

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
    public void save(Room room, List<AdditionalServices> services, Customer customer, Date checkInDate, Date checkOutDate) {
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setCustomer(customer);
        booking.setAdditionalServices(services);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setPayment(null);
        booking.setBookingDate(Timestamp.from(Instant.now()));
        BookingState bookingState = bookingStateFactory.getBookingState(BookingStatus.PENDING);
        booking.transitionToState(bookingState);
        bookingRepository.save(booking);
    }


    @Override
    public List<Booking> getOrdersByCustomer(Customer customer) {
        return bookingRepository.findBookingByCustomer(customer);
    }

    @Override
    public void cancelOrder(Long bookingId) {
        Optional<Booking> optional = bookingRepository.findById(bookingId);
        Booking booking = null;

        //we get the booking from optional
        if(optional.isPresent()){
            booking = optional.get();
            booking.cancel();
            BookingState bookingState = bookingStateFactory.getBookingState(BookingStatus.CANCELLED);
            booking.transitionToState(bookingState);
            bookingRepository.save(booking);

            Room room = booking.getRoom();
            room.setAvailable(true);
            roomRepository.save(room);

            bookingRepository.deleteBookingByBookingId(bookingId);

        }else{
            throw new RuntimeException("Booking not found for id ::" + bookingId);
        }


    }
}

