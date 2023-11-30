package com.project.hotelreservation.model.entity;

import com.project.hotelreservation.decorator.IPayment;
import com.project.hotelreservation.enums.PaymentMethod;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "payment")
public class Payment implements IPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date")
    private Date paymentDate;

    @Column(name = "method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Override
    public void adjustAmount(double adjustment) {
        this.amount += adjustment;
    }
}
