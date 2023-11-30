package com.project.hotelreservation.decorator;

import com.project.hotelreservation.model.entity.Customer;

public class WithMemberDiscount extends PaymentDecorator {
    private final Customer customer;

    public WithMemberDiscount(IPayment payment, Customer customer) {
        super(payment);
        this.customer = customer;
    }
    @Override
    public double getAmount() {
        double discount = calculateDiscount(customer.getRewardPoints());
        double originalAmount = payment.getAmount();
        return Math.max(originalAmount - discount, 0); // make sure the final amount is not negative
    }
    @Override
    public void adjustAmount(double adjustment) {
        payment.adjustAmount(adjustment);
    }

    private double calculateDiscount(int rewardPoints) {
        // every 100 points = 1 euro discount
        return rewardPoints / 100.0;
    }

}