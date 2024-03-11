package com.simplestore.paymentservice.repositories;

import com.simplestore.paymentservice.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
