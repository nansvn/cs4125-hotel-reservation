package com.project.hotelreservation.repository;
import com.project.hotelreservation.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.hotelreservation.model.entity.Booking;
import com.project.hotelreservation.model.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Define custom queries or methods for Payment entity if needed
    Payment findByBooking(Booking booking);
}

