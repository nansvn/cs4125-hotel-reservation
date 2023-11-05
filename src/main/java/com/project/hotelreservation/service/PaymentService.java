package com.project.hotelreservation.service;

import com.project.hotelreservation.enums.PaymentMethod;
import com.project.hotelreservation.model.entity.Payment;


import java.util.Date;


public interface PaymentService {
    void makePayment(Payment payment, boolean hasMealDeal, boolean useRewardPoints);
}
