package com.project.hotelreservation.decorator;

import com.project.hotelreservation.model.entity.Payment;
import lombok.AllArgsConstructor;

/**
 * @author Nan
 */
@AllArgsConstructor
public abstract class PaymentDecorator {
    protected Payment payment;

    public abstract Payment decorate();
}
