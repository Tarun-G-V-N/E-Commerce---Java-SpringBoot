package com.simplestore.paymentservice.paymentgateways;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGateway {

    public String generatePaymentLink(long orderId, long amount, String phoneNumber) throws RazorpayException, StripeException;
}
