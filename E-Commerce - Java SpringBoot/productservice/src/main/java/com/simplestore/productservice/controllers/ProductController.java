package com.simplestore.productservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplestore.productservice.clients.UserServiceClient;
import com.simplestore.productservice.dtos.*;
import com.simplestore.productservice.exceptions.InvalidTitleException;
import com.simplestore.productservice.exceptions.InvalidTokenException;
import com.simplestore.productservice.exceptions.ProductNotFoundException;
import com.simplestore.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {
        private ProductService productService;
        private UserServiceClient userServiceClient;

        @Autowired
        public ProductController(@Qualifier("productServiceImpl") ProductService productService, UserServiceClient userServiceClient) {

            this.productService = productService;
            this.userServiceClient = userServiceClient;
        }

        @GetMapping("/products/{id}")
        public ResponseEntity<ProductResponseDTO> getProductByID(@PathVariable UUID id, @RequestHeader String token) throws ProductNotFoundException{

            ProductResponseDTO productResponseDTO = productService.getProductById(id);
            return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
//            try {
//                ProductResponseDTO productResponseDTO = productService.getProductById(id);
//                return ResponseEntity.ok(productResponseDTO);
//            }
//            catch (ProductNotFoundException exception) {
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with ID: "+id+" not available in the store!!");
//            }
        }
        @GetMapping("/products/title/{title}")
        public ResponseEntity<ProductResponseDTO> getProductByTitle(@PathVariable String title, @RequestHeader String token) throws ProductNotFoundException, InvalidTitleException {

            ProductResponseDTO productResponseDTO = productService.getProductByTitle(title);
            return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
        }

        @GetMapping("/products")
        public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {

            List<ProductResponseDTO> productResponseDTOS = productService.getAllProducts();
            return new ResponseEntity<>(productResponseDTOS, HttpStatus.OK);
        }
        @PostMapping("/products")
        public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO, @RequestHeader String token) {

            ProductResponseDTO productResponseDTO = productService.createProduct(productRequestDTO);
            return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
        }
        @PutMapping("/products/{id}")
        public ResponseEntity<String> updateProduct(@PathVariable UUID id, @RequestBody ProductRequestDTO productRequestDTO, @RequestHeader String token) {

            if(productService.updateProduct(id, productRequestDTO)) return new ResponseEntity<>("Product updated Successfully", HttpStatus.OK);

            return new ResponseEntity<>("Your request is not valid!!", HttpStatus.BAD_REQUEST);
        }
        @DeleteMapping("/products/{id}")
        public ResponseEntity<String> deleteProduct(@PathVariable UUID id, @RequestHeader String token) {

            if(productService.deleteProduct(id)) return new ResponseEntity<>("Product is deleted Successfully", HttpStatus.OK);

            return new ResponseEntity<>("Your request is not valid!!", HttpStatus.BAD_REQUEST);
        }

        private void validateToken(String token) throws JsonProcessingException {
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            ObjectMapper mapper = new ObjectMapper();
            JWTPayloadDTO payloadDTO = mapper.readValue(payload, JWTPayloadDTO.class);
            int userId = payloadDTO.getUserId();
            ValidateTokenDTO validateTokenDTO = new ValidateTokenDTO(userId, token);
            String result = userServiceClient.validateToken(validateTokenDTO);

            if(!result.contains(SessionStatus.Active.name())) {
                throw new InvalidTokenException("Token is not valid!");
            }
        }
    }

