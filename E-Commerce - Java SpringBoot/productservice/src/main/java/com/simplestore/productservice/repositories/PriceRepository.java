package com.simplestore.productservice.repositories;

import com.simplestore.productservice.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PriceRepository extends JpaRepository<Price, UUID> {
    Price findByAmount(double amount);
}
