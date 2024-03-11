package com.simplestore.paymentservice.controllers;

import com.razorpay.RazorpayException;
import com.simplestore.paymentservice.dtos.PaymentRequestDto;
import com.simplestore.paymentservice.services.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public ResponseEntity<String> initiatePayment(@RequestBody PaymentRequestDto paymentRequestDto) throws RazorpayException, StripeException {

        String response = paymentService.initiatePayment(paymentRequestDto.getOrderId(), paymentRequestDto.getAmount(), paymentRequestDto.getPhoneNumber());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
