package com.simplestore.productservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplestore.productservice.dtos.ProductResponseDTO;
import com.simplestore.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean(name = "productServiceImpl")
    private ProductService productService;

    private String convertToJson(Object object) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
    @Test
    public void testGetProductByIdSuccess() throws Exception {

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(UUID.fromString("feecadf2-e74c-4a06-9e32-2e6d757158b2"));
        productResponseDTO.setTitle("Apple Iphone 12");
        productResponseDTO.setDescription("Brand New Iphone");
        productResponseDTO.setCategory("Electronics");
        productResponseDTO.setPrice(100000.00);
        productResponseDTO.setImage("ImageURL");

        String responseString = convertToJson(productResponseDTO);

        Mockito.when(productService.getProductById(UUID.fromString("feecadf2-e74c-4a06-9e32-2e6d757158b2"))).thenReturn(productResponseDTO);

        mockMvc.perform(get("/products/feecadf2-e74c-4a06-9e32-2e6d757158b2"))
                .andExpect(status().is(200))
                .andExpect(content().string(responseString));
    }

    @Test
    public void testGetProductByTitleSuccess() throws Exception {

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(UUID.fromString("feecadf2-e74c-4a06-9e32-2e6d757158b2"));
        productResponseDTO.setTitle("Apple Iphone 12");
        productResponseDTO.setDescription("Brand New Iphone");
        productResponseDTO.setCategory("Electronics");
        productResponseDTO.setPrice(100000.00);
        productResponseDTO.setImage("ImageURL");

        String responseString = convertToJson(productResponseDTO);

        Mockito.when(productService.getProductByTitle("Apple Iphone 12")).thenReturn(productResponseDTO);

        mockMvc.perform(get("/products/title/Apple Iphone 12"))
                .andExpect(status().is(200))
                .andExpect(content().string(responseString));
    }
}
