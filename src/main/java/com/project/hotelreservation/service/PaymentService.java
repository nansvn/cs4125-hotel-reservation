package com.project.hotelreservation.service;

import com.project.hotelreservation.enums.PaymentMethod;
import com.project.hotelreservation.model.entity.Booking;
import com.project.hotelreservation.model.entity.Customer;
import com.project.hotelreservation.model.entity.Payment;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;

@Transactional
public interface PaymentService {
    void initial(Booking booking);

    void makePayment(Payment payment,
                     Customer customer,
                     PaymentMethod paymentMethod,
                     boolean hasMealDeal,
                     boolean useRewardPoints);

    Payment findPaymentByBooking(Booking booking);

    void deletePayment(Payment payment);
}
