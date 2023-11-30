package com.project.hotelreservation.service.serviceImpl;

import com.project.hotelreservation.decorator.IPayment;
import com.project.hotelreservation.decorator.WithMealDeal;
import com.project.hotelreservation.decorator.WithMemberDiscount;
import com.project.hotelreservation.enums.Status;
import com.project.hotelreservation.enums.PaymentMethod;
import com.project.hotelreservation.model.entity.*;
import com.project.hotelreservation.repository.PaymentRepository;
import com.project.hotelreservation.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;

    @Override
    public void initial(Booking booking) {
        // Check if a payment already exists for the booking
        Payment existingPayment = paymentRepository.findByBooking(booking);

        if (existingPayment == null) {
            Payment payment = new Payment();
            // set booking
            payment.setBooking(booking);
            // calculate and set amount
            double roomPrice = getRoomPrice(booking);
            double servicePrice = 0.0;
            if (!booking.getAdditionalServices().isEmpty()) {
                // calculate the service fees if available
                for (AdditionalServices service : booking.getAdditionalServices()) {
                    servicePrice += service.getPrice();
                }
            }
            payment.setAmount(roomPrice + servicePrice);

            // set status
            payment.setStatus(String.valueOf(Status.PENDING));

            // Set the payment in the booking
            booking.setPayment(payment);

            paymentRepository.save(payment);
        }
    }

    @Override
    public void makePayment(Payment payment,
                            Customer customer,
                            PaymentMethod paymentMethod,
                            boolean hasMealDeal,
                            boolean useRewardPoints) {
        IPayment decoratedPayment = payment;
        if (hasMealDeal) {
            decoratedPayment = new WithMealDeal(decoratedPayment);
        }
        if (useRewardPoints) {
            decoratedPayment = new WithMemberDiscount(decoratedPayment, customer);
        }
        try {
            // update amount
            double finalAmount = decoratedPayment.getAmount();
            payment.setAmount(finalAmount);

            // set payment date, method, status
            Date currentDate = new Date();
            payment.setPaymentDate(currentDate);
            payment.setPaymentMethod(paymentMethod);
            payment.setStatus(String.valueOf(Status.COMPLETED));

            // save the updated Payment entity
            paymentRepository.save(payment);
        } catch (Exception e) {
            // error handler here
            System.err.println("Error during payment processing: " + e.getMessage());
            throw e;
        }
    }

    private static double getRoomPrice(Booking booking) {
        Room room = booking.getRoom();
        Date checkInDate = booking.getCheckInDate();
        Date checkOutDate = booking.getCheckOutDate();
        // get the difference in milliseconds
        long differenceInMillis = checkOutDate.getTime() - checkInDate.getTime();
        // convert the difference to days
        int duration = (int) (differenceInMillis / (1000 * 60 * 60 * 24)) + 1;
        // get total price
        return room.getPricePerNight().doubleValue() * duration;
    }


    @Override
    public Payment findPaymentByBooking(Booking booking) {
        return paymentRepository.findByBooking(booking);
    }

    public void deletePayment(Payment payment) {
        if (payment != null) {
            paymentRepository.delete(payment);
        }
    }

}