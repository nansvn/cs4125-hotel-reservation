package com.project.hotelreservation.model.entity;

import com.project.hotelreservation.enums.BookingStatus;
import com.project.hotelreservation.model.BookingState;
import com.project.hotelreservation.model.PendingState;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
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
    private Timestamp bookingDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Transient
    private BookingState bookingState;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne(mappedBy = "booking")
    private Payment payment;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "booking_services",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<AdditionalServices> additionalServices;

    public Booking() {
        this.status = BookingStatus.PENDING;
        this.bookingState = new PendingState();
    }

    public void transitionToState(BookingState newState) {
        this.bookingState = newState;
        this.status = newState.getStatus();
    }

    public void process() {
        bookingState.handle(this);
    }

    public void cancel() {
        bookingState.handle(this);
    }

    public void complete() {
        bookingState.handle(this);
    }
}
