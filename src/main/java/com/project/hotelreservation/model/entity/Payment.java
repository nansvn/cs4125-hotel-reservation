package com.project.hotelreservation.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
@Data
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private Date paymentDate;

    @OneToOne
    private Booking booking;
}
