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

    void makePayment(Booking booking,
                     Customer customer,
                     PaymentMethod paymentMethod,
                     boolean hasMealDeal,
                     boolean useRewardPoints);
    void deletePayment(Payment payment);
}
