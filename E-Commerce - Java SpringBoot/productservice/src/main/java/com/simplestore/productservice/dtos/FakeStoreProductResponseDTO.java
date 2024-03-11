package com.simplestore.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FakeStoreProductResponseDTO {
    private UUID id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
}
