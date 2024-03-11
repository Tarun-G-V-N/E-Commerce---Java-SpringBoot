package com.simplestore.productservice.services;

import com.simplestore.productservice.dtos.ProductRequestDTO;
import com.simplestore.productservice.dtos.ProductResponseDTO;
import com.simplestore.productservice.exceptions.InvalidTitleException;
import com.simplestore.productservice.exceptions.ProductNotFoundException;
import com.simplestore.productservice.mappers.ProductMapper;
import com.simplestore.productservice.models.Category;
import com.simplestore.productservice.models.Price;
import com.simplestore.productservice.models.Product;
import com.simplestore.productservice.repositories.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.simplestore.productservice.utils.ProductUtil.isNull;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private CategoryRepository categoryRepository;
    private PriceRepository priceRepository;
    private OpenSearchProductRepository openSearchProductRepository;
    public ProductServiceImpl(ProductRepository productRepository, OrderRepository orderRepository, CategoryRepository categoryRepository, PriceRepository priceRepository, OpenSearchProductRepository openSearchProductRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.orderRepository = orderRepository;
        this.priceRepository = priceRepository;
        this.openSearchProductRepository = openSearchProductRepository;
    }

    public Category getCategoryObject(String categoryName) {

        Category category = categoryRepository.findByCategoryName(categoryName);
        if(isNull(category)) {

            category = new Category(categoryName);
            categoryRepository.save(category);
            category = categoryRepository.findByCategoryName(categoryName);
        }

        return category;
    }

    public Price getPriceObject(double amount) {

        Price price = priceRepository.findByAmount(amount);

        if(isNull(price)) {

            price = new Price("INR", amount, 0.0);
            priceRepository.save(price);
            price = priceRepository.findByAmount(amount);
        }

        return price;
    }
    @Override
    public List<ProductResponseDTO> getAllProducts() {

        List<Product> products = productRepository.findAll();
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();

        for(Product product : products) {

            productResponseDTOS.add(ProductMapper.productToProductResponseDTO(product));
        }

        return productResponseDTOS;
    }

    @Override
    public ProductResponseDTO getProductById(UUID id) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) throw new ProductNotFoundException(id);
        return ProductMapper.productToProductResponseDTO(optionalProduct.get());
    }

    @Override
    public ProductResponseDTO getProductByTitle(String title) throws ProductNotFoundException, InvalidTitleException{

        if(title == null || title.isEmpty()) throw new InvalidTitleException(title);
        Product product = productRepository.findByTitle(title);
        if(isNull(product)) throw new ProductNotFoundException(title);
        return ProductMapper.productToProductResponseDTO(product);
    }


    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {

        Product productToSave = new Product();

        Category category = getCategoryObject(productRequestDTO.getCategory());
        Price price = getPriceObject(productRequestDTO.getPrice());

        productToSave.setTitle(productRequestDTO.getTitle());
        productToSave.setDescription(productRequestDTO.getDescription());
        productToSave.setCategory(category);
        productToSave.setPrice(price);
        productToSave.setImage(productRequestDTO.getImage());

        Product product = productRepository.save(productToSave);
        openSearchProductRepository.save(product);

        return ProductMapper.productToProductResponseDTO(product);
    }

    @Override
    public boolean updateProduct(UUID id, ProductRequestDTO productRequestDTO) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) throw new ProductNotFoundException(id);
        Product product = optionalProduct.get();

        Category category = getCategoryObject(productRequestDTO.getCategory());
        Price price = getPriceObject(productRequestDTO.getPrice());

        if(product.getPrice().getId() != price.getId()) priceRepository.deleteById(product.getPrice().getId());

        product.setTitle(productRequestDTO.getTitle());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(price);
        product.setCategory(category);
        product.setImage(productRequestDTO.getImage());
        productRepository.save(product);
        return true;
    }

    @Override
    public boolean deleteProduct(UUID id) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) throw new ProductNotFoundException(id);
        productRepository.deleteById(id);
        return true;
    }
}
