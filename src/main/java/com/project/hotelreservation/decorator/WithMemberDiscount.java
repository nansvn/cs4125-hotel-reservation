package com.project.hotelreservation.decorator;

import com.project.hotelreservation.model.entity.Payment;

public class WithMemberDiscount extends PaymentDecorator {

    public WithMemberDiscount(Payment payment) {
        super(payment);
    }

    @Override
    public Payment decorate() {
        double reward_point = payment.getBooking().getCustomer().getRewardPoints();
        // every 100 points will be turned to 1 euro discount
        double discount = reward_point / 100;
        // deduct discount from total payment amount
        payment.setAmount(payment.getAmount() - discount);
        // remove reward point from users' account
        payment.getBooking().getCustomer().setRewardPoints(0);
        return payment;
    }
}
