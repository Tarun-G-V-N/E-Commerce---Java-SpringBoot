package com.simplestore.productservice.services;

import com.simplestore.productservice.dtos.ProductResponseDTO;
import com.simplestore.productservice.exceptions.InvalidTitleException;
import com.simplestore.productservice.exceptions.ProductNotFoundException;
import com.simplestore.productservice.models.Category;
import com.simplestore.productservice.models.Price;
import com.simplestore.productservice.models.Product;
import com.simplestore.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;
    @Test
    public void testGetProductByTitleSuccess() throws ProductNotFoundException, InvalidTitleException {

        //Arrange
        Category mockCategory = new Category("Electronics");
        Price mockPrice = new Price("INR", 100000.00, 10.0);
        String testTitle = "Apple Iphone 12";
        Product mockProduct = new Product(testTitle, mockPrice, "Brand New Iphone", mockCategory, "image URL");
        mockProduct.setId(UUID.randomUUID());
        Mockito.when(productRepository.findByTitle(testTitle)).thenReturn(mockProduct);

        //Act
        ProductResponseDTO actualResponse = productService.getProductByTitle(testTitle);

        //Assert
        Assertions.assertEquals(actualResponse.getId(), mockProduct.getId());
        Assertions.assertEquals(actualResponse.getTitle(), mockProduct.getTitle());
        Assertions.assertEquals(actualResponse.getPrice(), mockProduct.getPrice().getAmount());
        Assertions.assertEquals(actualResponse.getCategory(), mockProduct.getCategory().getCategoryName());
        Assertions.assertEquals(actualResponse.getDescription(), mockProduct.getDescription());
        Assertions.assertEquals(actualResponse.getImage(), mockProduct.getImage());
    }

    @Test
    public void testGetProductByIdSuccess() throws ProductNotFoundException {

        //Arrange
        Category mockCategory = new Category("Electronics");
        Price mockPrice = new Price("INR", 100000.00, 10.0);
        String testTitle = "Apple Iphone 12";
        Product mockProduct = new Product(testTitle, mockPrice, "Brand New Iphone", mockCategory, "image URL");
        mockProduct.setId(UUID.fromString("feecadf2-e74c-4a06-9e32-2e6d757158b2"));
        Mockito.when(productRepository.findById(UUID.fromString("feecadf2-e74c-4a06-9e32-2e6d757158b2"))).thenReturn(Optional.of(mockProduct));

        //Act
        ProductResponseDTO actualResponse = productService.getProductById(UUID.fromString("feecadf2-e74c-4a06-9e32-2e6d757158b2"));

        //Assert
        Assertions.assertEquals(actualResponse.getId(), mockProduct.getId());
        Assertions.assertEquals(actualResponse.getTitle(), mockProduct.getTitle());
        Assertions.assertEquals(actualResponse.getPrice(), mockProduct.getPrice().getAmount());
        Assertions.assertEquals(actualResponse.getCategory(), mockProduct.getCategory().getCategoryName());
        Assertions.assertEquals(actualResponse.getDescription(), mockProduct.getDescription());
        Assertions.assertEquals(actualResponse.getImage(), mockProduct.getImage());
    }
    @Test
    public void testGetAllProducts() {

        //Arrange
        Category mockCategory1 = new Category("Electronics");
        Category mockCategory2 = new Category("Apparel");
        Price mockPrice1 = new Price("INR", 100000.00, 0.0);
        Price mockPrice2 = new Price("INR", 500.00, 10.0);

        Product mockProduct1 = new Product("Apple Iphone 12", mockPrice1, "Brand New Iphone", mockCategory1, "image URL");
        mockProduct1.setId(UUID.fromString("feecadf2-e74c-4a06-9e32-2e6d757158b2"));
        Product mockProduct2 = new Product("Superman T-Shirt", mockPrice1, "Nice T-Shirt with Superman logo", mockCategory1, "image URL");
        mockProduct2.setId(UUID.fromString("a142d1c2-5f8d-4773-8220-7d892f096e7d"));
        List<Product> mockProductList = List.of(mockProduct1, mockProduct2);

        Mockito.when(productRepository.findAll()).thenReturn(mockProductList);

        //Act
        List<ProductResponseDTO> actualproductResponseDTOS = productService.getAllProducts();

        //Assert
        Assertions.assertEquals(actualproductResponseDTOS.get(0).getId(), mockProduct1.getId());
        Assertions.assertEquals(actualproductResponseDTOS.get(0).getTitle(), mockProduct1.getTitle());
        Assertions.assertEquals(actualproductResponseDTOS.get(0).getPrice(), mockProduct1.getPrice().getAmount());
        Assertions.assertEquals(actualproductResponseDTOS.get(0).getCategory(), mockProduct1.getCategory().getCategoryName());
        Assertions.assertEquals(actualproductResponseDTOS.get(0).getDescription(), mockProduct1.getDescription());
        Assertions.assertEquals(actualproductResponseDTOS.get(0).getImage(), mockProduct1.getImage());

        Assertions.assertEquals(actualproductResponseDTOS.get(1).getId(), mockProduct2.getId());
        Assertions.assertEquals(actualproductResponseDTOS.get(1).getTitle(), mockProduct2.getTitle());
        Assertions.assertEquals(actualproductResponseDTOS.get(1).getPrice(), mockProduct2.getPrice().getAmount());
        Assertions.assertEquals(actualproductResponseDTOS.get(1).getCategory(), mockProduct2.getCategory().getCategoryName());
        Assertions.assertEquals(actualproductResponseDTOS.get(1).getDescription(), mockProduct2.getDescription());
        Assertions.assertEquals(actualproductResponseDTOS.get(1).getImage(), mockProduct2.getImage());
    }
}
