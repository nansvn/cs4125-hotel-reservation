package com.project.hotelreservation.service.serviceImpl;

import com.project.hotelreservation.decorator.WithMealDeal;
import com.project.hotelreservation.decorator.WithMemberDiscount;
import com.project.hotelreservation.enums.Status;
import com.project.hotelreservation.enums.PaymentMethod;
import com.project.hotelreservation.model.entity.AdditionalServices;
import com.project.hotelreservation.model.entity.Booking;
import com.project.hotelreservation.model.entity.Payment;
import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.repository.PaymentRepository;
import com.project.hotelreservation.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;

    @Override
    public void initial(Booking booking) {
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

        paymentRepository.save(payment);
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

    public void makePayment(Payment payment, PaymentMethod paymentMethod, boolean hasMealDeal, boolean useRewardPoints) {
        if (hasMealDeal) {
            payment = new WithMealDeal(payment).decorate();
        }
        if (useRewardPoints) {
            payment = new WithMemberDiscount(payment).decorate();
        }
        Date currentDate = new Date();
        payment.setPaymentDate(currentDate);
        payment.setPaymentMethod(paymentMethod);
        payment.setStatus(String.valueOf(Status.COMPLETED));
        paymentRepository.save(payment);
    }

    @Override
    public Payment findPaymentByBooking(Booking booking) {
        return paymentRepository.findByBooking(booking);
    }
}
