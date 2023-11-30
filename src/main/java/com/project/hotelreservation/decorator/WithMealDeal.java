package com.project.hotelreservation.decorator;

public class WithMealDeal extends PaymentDecorator {
    public WithMealDeal(IPayment payment) {
        super(payment);
    }
    @Override
    public double getAmount() {
        double meal_price = 10;
        return payment.getAmount() + meal_price; // adding a fixed meal price
    }

    @Override
    public void adjustAmount(double adjustment) {
        payment.adjustAmount(adjustment);
    }
}