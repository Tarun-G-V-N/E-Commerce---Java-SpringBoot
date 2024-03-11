package com.simplestore.productservice.repositories;

import com.simplestore.productservice.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Product findByTitle(String title);
    List<Product> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
    void deleteById(UUID uuid);
}
