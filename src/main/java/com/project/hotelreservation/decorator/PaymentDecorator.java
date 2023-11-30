package com.project.hotelreservation.decorator;

public abstract class PaymentDecorator implements IPayment {
    protected IPayment payment;

    public PaymentDecorator(IPayment payment) {
        this.payment = payment;
    }
}
