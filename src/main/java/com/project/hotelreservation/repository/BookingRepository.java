package com.project.hotelreservation.repository;

import com.project.hotelreservation.model.entity.Booking;
import com.project.hotelreservation.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findBookingByCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(Date checkInDate, Date checkOutDate);

    List<Booking> findBookingByCustomer(Customer customer);

    void deleteBookingByBookingId(Long bookingId);
}
