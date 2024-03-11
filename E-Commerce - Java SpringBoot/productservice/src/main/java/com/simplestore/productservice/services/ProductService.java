package com.simplestore.productservice.services;

import com.simplestore.productservice.dtos.ProductRequestDTO;
import com.simplestore.productservice.dtos.ProductResponseDTO;
import com.simplestore.productservice.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProductById(UUID id);

    ProductResponseDTO getProductByTitle(String title);
    ProductResponseDTO createProduct(ProductRequestDTO product);

    boolean updateProduct(UUID id, ProductRequestDTO productRequestDTO);

    boolean deleteProduct(UUID id);

}
