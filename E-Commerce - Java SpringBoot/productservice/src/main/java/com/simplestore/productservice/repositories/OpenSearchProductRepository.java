package com.simplestore.productservice.repositories;

import com.simplestore.productservice.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface OpenSearchProductRepository extends ElasticsearchRepository<Product, Long> {
    List<Product> findAllByTitleContainingIgnoreCase(String query, Pageable pageable);
}
