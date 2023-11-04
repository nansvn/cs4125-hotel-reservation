package com.project.hotelreservation.service;

import com.project.hotelreservation.enums.PaymentMethod;
import com.project.hotelreservation.model.entity.Booking;
import com.project.hotelreservation.model.entity.Payment;
import com.project.hotelreservation.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.hotelreservation.service.BookingService;
import com.project.hotelreservation.controller.PaymentController;

import java.util.Date;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private BookingService bookingService;

    public Payment makePayment(double amount, PaymentMethod paymentMethod) {

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentDate(new Date());

        payment.setStatus("PENDING"); // Set an initial status

        return paymentRepository.save(payment);
    }

    // Add other methods as needed, such as retrieving payments or updating payment status
}
