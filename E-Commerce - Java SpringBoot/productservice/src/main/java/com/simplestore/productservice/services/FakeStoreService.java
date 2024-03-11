package com.simplestore.productservice.services;

import com.simplestore.productservice.clients.FakeStoreClient;
import com.simplestore.productservice.dtos.FakeStoreProductRequestDTO;
import com.simplestore.productservice.dtos.FakeStoreProductResponseDTO;
import com.simplestore.productservice.dtos.ProductRequestDTO;
import com.simplestore.productservice.dtos.ProductResponseDTO;
import com.simplestore.productservice.exceptions.ProductNotFoundException;
import com.simplestore.productservice.mappers.ProductMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.simplestore.productservice.utils.ProductUtil.isNull;

@Service
public class FakeStoreService implements ProductService{
    private final RestTemplateBuilder restTemplateBuilder;
    private final FakeStoreClient fakeStoreClient;
    private final RedisTemplate<String, FakeStoreProductResponseDTO> redisTemplate;
    public FakeStoreService (RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient, RedisTemplate redisTemplate) {

        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {

        List<FakeStoreProductResponseDTO> fakeStoreProductResponseDTOS = fakeStoreClient.getAllProducts();
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();

        for(FakeStoreProductResponseDTO fakeStoreProductResponseDTO : fakeStoreProductResponseDTOS) {

            productResponseDTOS.add(ProductMapper.getProductResponseDTO(fakeStoreProductResponseDTO));
        }

        return productResponseDTOS;
    }

    @Override
    public ProductResponseDTO getProductById(UUID id) throws ProductNotFoundException{

        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = (FakeStoreProductResponseDTO) redisTemplate.opsForHash().get("PRODUCTS", id);
        if(isNull(fakeStoreProductResponseDTO)) fakeStoreProductResponseDTO = fakeStoreClient.getProductById(id);
        if(isNull(fakeStoreProductResponseDTO)) throw new ProductNotFoundException(id);
        redisTemplate.opsForHash().put("PRODUCTS", id, fakeStoreProductResponseDTO);
        return ProductMapper.getProductResponseDTO(fakeStoreProductResponseDTO);
    }

    @Override
    public ProductResponseDTO getProductByTitle(String title) {
        return null;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {

        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = ProductMapper.getFakeStoreProductRequestDTO(productRequestDTO);
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreClient.createProduct(fakeStoreProductRequestDTO);
        ProductResponseDTO productResponseDTO = ProductMapper.getProductResponseDTO(fakeStoreProductResponseDTO);

        return productResponseDTO;
    }

    @Override
    public boolean updateProduct(UUID id, ProductRequestDTO productRequestDTO) {

        ProductResponseDTO productResponseDTO = getProductById(id);
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = ProductMapper.getFakeStoreProductRequestDTO(productRequestDTO);
        Boolean isProductUpdated = fakeStoreClient.updateProduct(id, fakeStoreProductRequestDTO);
        return isProductUpdated;
    }

    @Override
    public boolean deleteProduct(UUID  id) {

        ProductResponseDTO productResponseDTO = getProductById(id);
        return fakeStoreClient.deleteProduct(id);
    }
}
