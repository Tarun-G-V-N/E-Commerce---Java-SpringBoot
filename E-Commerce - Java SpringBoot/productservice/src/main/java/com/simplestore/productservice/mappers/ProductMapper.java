package com.simplestore.productservice.mappers;

import com.simplestore.productservice.dtos.FakeStoreProductRequestDTO;
import com.simplestore.productservice.dtos.FakeStoreProductResponseDTO;
import com.simplestore.productservice.dtos.ProductRequestDTO;
import com.simplestore.productservice.dtos.ProductResponseDTO;
import com.simplestore.productservice.models.Product;

public class ProductMapper {

    public static FakeStoreProductRequestDTO getFakeStoreProductRequestDTO(ProductRequestDTO productRequestDTO) {

        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = new FakeStoreProductRequestDTO();

        fakeStoreProductRequestDTO.setTitle(productRequestDTO.getTitle());
        fakeStoreProductRequestDTO.setCategory(productRequestDTO.getCategory());
        fakeStoreProductRequestDTO.setDescription(productRequestDTO.getDescription());
        fakeStoreProductRequestDTO.setPrice(productRequestDTO.getPrice());
        fakeStoreProductRequestDTO.setImage(productRequestDTO.getImage());

        return fakeStoreProductRequestDTO;
    }

    public static ProductResponseDTO getProductResponseDTO(FakeStoreProductResponseDTO fakeStoreProductResponseDTO) {

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId(fakeStoreProductResponseDTO.getId());
        productResponseDTO.setTitle(fakeStoreProductResponseDTO.getTitle());
        productResponseDTO.setCategory(fakeStoreProductResponseDTO.getCategory());
        productResponseDTO.setDescription(fakeStoreProductResponseDTO.getDescription());
        productResponseDTO.setPrice(fakeStoreProductResponseDTO.getPrice());
        productResponseDTO.setImage(fakeStoreProductResponseDTO.getImage());

        return productResponseDTO;
    }

    public static ProductResponseDTO productToProductResponseDTO(Product product) {

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId(product.getId());
        productResponseDTO.setTitle(product.getTitle());
        productResponseDTO.setCategory(product.getCategory().getCategoryName());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setPrice(product.getPrice().getAmount());
        productResponseDTO.setImage(product.getImage());

        return productResponseDTO;
    }
}
