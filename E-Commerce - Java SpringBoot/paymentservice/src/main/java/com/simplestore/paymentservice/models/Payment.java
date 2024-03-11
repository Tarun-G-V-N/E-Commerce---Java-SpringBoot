package com.simplestore.paymentservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Payment extends BaseModel{

    private long orderId;
    private long amount;
    private Date dateOfPayment;
    private String paymentLink;
    @Enumerated(EnumType.ORDINAL)
    private PaymentType paymentType;
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;
}
