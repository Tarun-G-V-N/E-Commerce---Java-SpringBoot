package com.simplestore.productservice.services;

import com.simplestore.productservice.dtos.ProductResponseDTO;
import com.simplestore.productservice.mappers.ProductMapper;
import com.simplestore.productservice.models.Product;
import com.simplestore.productservice.models.SortOrder;
import com.simplestore.productservice.models.SortParam;
import com.simplestore.productservice.repositories.OpenSearchProductRepository;
import com.simplestore.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    private OpenSearchProductRepository openSearchProductRepository;

    public SearchService(ProductRepository productRepository, OpenSearchProductRepository openSearchProductRepository) {
        this.openSearchProductRepository = openSearchProductRepository;
    }
    public List<ProductResponseDTO> searchProduct(String query, int pageSize, int pageNumber, List<SortParam> sortParams) {

        Sort sortRequest = sortParams.get(0).getSortOrder().equals(SortOrder.Ascending) ? Sort.by(sortParams.get(0).getParamName()).ascending() : Sort.by(sortParams.get(0).getParamName()).descending();

        for(int i=1; i< sortParams.size(); i++) {
            if(sortParams.get(0).getSortOrder().equals(SortOrder.Ascending)) {
                sortRequest = sortRequest.and(Sort.by(sortParams.get(i).getParamName()).ascending());
            }
            else {
                sortRequest = sortRequest.and(Sort.by(sortParams.get(i).getParamName()).descending());
            }
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortRequest);
        List<Product> productList = openSearchProductRepository.findAllByTitleContainingIgnoreCase(query, pageRequest);
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for(Product product : productList) {
            productResponseDTOS.add(ProductMapper.productToProductResponseDTO(product));
        }
        return productResponseDTOS;
    }
}
