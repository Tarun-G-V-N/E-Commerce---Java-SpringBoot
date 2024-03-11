package com.simplestore.productservice.clients;

import com.simplestore.productservice.dtos.FakeStoreProductRequestDTO;
import com.simplestore.productservice.dtos.FakeStoreProductResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Component
public class FakeStoreClient {

    private final RestTemplateBuilder restTemplateBuilder;
    private String fakeStorebaseURL;
    private String fakeStoreProductPath;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakestore.api.url}") String fakeStorebaseURL, @Value("${fakstore.api.path.product}") String fakeStoreProductPath) {

        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStorebaseURL = fakeStorebaseURL;
        this.fakeStoreProductPath = fakeStoreProductPath;
    }

    public FakeStoreProductResponseDTO getProductById(UUID id) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        String fakeStoreURL = fakeStorebaseURL + fakeStoreProductPath + id;
        ResponseEntity<FakeStoreProductResponseDTO> productResponse = restTemplate.getForEntity(fakeStoreURL, FakeStoreProductResponseDTO.class);
        return productResponse.getBody();
    }

    public List<FakeStoreProductResponseDTO> getAllProducts() {

        RestTemplate restTemplate = restTemplateBuilder.build();
        String fakeStoreURL = fakeStorebaseURL + fakeStoreProductPath;
        ResponseEntity<FakeStoreProductResponseDTO[]> productsResponse = restTemplate.getForEntity(fakeStoreURL, FakeStoreProductResponseDTO[].class);
        return List.of(productsResponse.getBody());
    }

    public FakeStoreProductResponseDTO createProduct(FakeStoreProductRequestDTO fakeStoreProductRequestDTO) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        String fakeStoreURL = fakeStorebaseURL + fakeStoreProductPath;
        ResponseEntity<FakeStoreProductResponseDTO> productResponse = restTemplate.postForEntity(fakeStoreURL, fakeStoreProductRequestDTO, FakeStoreProductResponseDTO.class);
        return productResponse.getBody();
    }

    public boolean updateProduct(UUID id, FakeStoreProductRequestDTO fakeStoreProductRequestDTO) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        String fakeStoreURL = fakeStorebaseURL + fakeStoreProductPath + id;
        restTemplate.put(fakeStoreURL, fakeStoreProductRequestDTO);
        return true;
    }

    public boolean deleteProduct(UUID id) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        String fakeStoreURL = fakeStorebaseURL + fakeStoreProductPath + id;
        restTemplate.delete(fakeStoreURL);
        return true;
    }
}
