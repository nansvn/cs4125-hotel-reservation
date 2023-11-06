package com.project.hotelreservation.service.serviceImpl;

import com.project.hotelreservation.decorator.WithMealDeal;
import com.project.hotelreservation.decorator.WithMemberDiscount;
import com.project.hotelreservation.model.entity.Payment;
import com.project.hotelreservation.repository.PaymentRepository;
import com.project.hotelreservation.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;

    public void makePayment(Payment payment, boolean hasMealDeal, boolean useRewardPoints) {
        if (hasMealDeal) {
            payment = new WithMealDeal(payment).decorate();
        }
        if (useRewardPoints) {
            payment = new WithMemberDiscount(payment).decorate();
        }
        paymentRepository.save(payment);
    }
}
