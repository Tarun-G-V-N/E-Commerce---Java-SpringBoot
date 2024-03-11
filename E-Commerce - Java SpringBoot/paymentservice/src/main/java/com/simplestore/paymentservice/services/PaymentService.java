package com.simplestore.paymentservice.services;

import com.razorpay.RazorpayException;
import com.simplestore.paymentservice.models.Payment;
import com.simplestore.paymentservice.paymentgateways.PaymentGateway;
import com.simplestore.paymentservice.repositories.PaymentRepository;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentGateway paymentGateway;
    private PaymentRepository paymentRepository;

    public PaymentService(@Qualifier("stripePaymentGateway") PaymentGateway paymentGateway, PaymentRepository paymentRepository) {

        this.paymentGateway = paymentGateway;
        this.paymentRepository = paymentRepository;
    }
    public String initiatePayment(long orderId, long amount, String phoneNumber) throws RazorpayException, StripeException {

        Payment payment = new Payment();
        payment.setOrderId(orderId);
        paymentRepository.save(payment);
        payment.setAmount(amount);

        return paymentGateway.generatePaymentLink(orderId, amount, phoneNumber);
    }

    public void savePaymentLink(String paymentLink) {

        Payment payment = new Payment();
        payment.setPaymentLink(paymentLink);
    }
}
