package com.simplestore.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {

    private long orderId;
    private long amount;
    private String phoneNumber;
}
