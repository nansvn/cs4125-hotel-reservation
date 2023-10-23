package com.project.hotelreservation.model.entity;

import com.project.hotelreservation.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date checkInDate;
    private Date checkOutDate;
    private Date bookingDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private Payment payment;

    @ManyToMany
    @JoinTable(
            name = "booking_services",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<AdditionalServices> additionalServices;

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
}

