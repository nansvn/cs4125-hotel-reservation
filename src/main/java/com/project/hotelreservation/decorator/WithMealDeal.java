package com.project.hotelreservation.decorator;

import com.project.hotelreservation.model.entity.Payment;

public class WithMealDeal extends PaymentDecorator {
    public WithMealDeal(Payment payment) {
        super(payment);
    }

    @Override
    public Payment decorate() {
        double meal_price = 10;
        payment.setAmount(payment.getAmount() + meal_price);
        return payment;
    }
}
