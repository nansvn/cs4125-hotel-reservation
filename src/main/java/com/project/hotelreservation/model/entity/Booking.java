package com.project.hotelreservation.model.entity;

import com.project.hotelreservation.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;
import com.project.hotelreservation.service.BookingState;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "checkin_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkInDate;

    @Column(name = "checkout_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkOutDate;

    @Column(name = "booking_date")
    private Date bookingDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne(mappedBy = "booking")
    private Payment payment;

    @ManyToMany
    @JoinTable(
            name = "booking_services",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<AdditionalServices> additionalServices;


    //private BookingState state;
    // You can manage the booking state using methods in the Booking class
    public void checkIn() {
        // Handle check-in logic for the booking
        // Transition to CheckedInState if valid
        if (this.status == BookingStatus.PENDING) {
            this.status = BookingStatus.COMPLETED;
        }
    }

    public void checkOut() {
        // Handle check-out logic for the booking
        // Transition to CheckedOutState
        if (this.status == BookingStatus.COMPLETED) {
            this.status = BookingStatus.COMPLETED;
        }
    }

    public void cancel() {
        // Handle cancellation logic for the booking
        // Transition to CancelledState
        if (this.status == BookingStatus.PENDING) {
            this.status = BookingStatus.CANCELLED;
        }
    }

}

