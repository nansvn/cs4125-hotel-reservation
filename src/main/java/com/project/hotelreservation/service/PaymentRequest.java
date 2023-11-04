package com.project.hotelreservation.service;

import com.project.hotelreservation.enums.PaymentMethod;
import lombok.Data;

@Data
public class PaymentRequest {
    private double amount;
    private PaymentMethod paymentMethod;
    private Long bookingId;
}


