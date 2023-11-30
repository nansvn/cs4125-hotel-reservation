package com.project.hotelreservation.decorator;

import com.project.hotelreservation.model.entity.Payment;

/**
 * @author Nan
 */
public interface IPayment {
    double getAmount();
    void adjustAmount(double adjustment);
}
