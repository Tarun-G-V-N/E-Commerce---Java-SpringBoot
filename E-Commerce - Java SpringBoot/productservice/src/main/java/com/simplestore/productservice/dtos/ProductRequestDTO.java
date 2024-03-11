package com.simplestore.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {

    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
}
