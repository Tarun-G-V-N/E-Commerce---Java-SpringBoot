package com.simplestore.productservice.controllers;

import com.simplestore.productservice.dtos.ProductResponseDTO;
import com.simplestore.productservice.dtos.SearchRequestDTO;
import com.simplestore.productservice.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private SearchService searchService;
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    @PostMapping
    public Page<ProductResponseDTO> searchProducts(SearchRequestDTO requestDTO) {

        List<ProductResponseDTO> productResponseDTOS = searchService.searchProduct(requestDTO.getQuery(), requestDTO.getItemsPerPage(), requestDTO.getPageNumber(), requestDTO.getSortParams());
        return new PageImpl<>(productResponseDTOS);
    }
}
