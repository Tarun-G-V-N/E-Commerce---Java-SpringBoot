package com.simplestore.productservice.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(UUID id) {

        super("Product with ID: "+id+" not available in the store!!");
    }

    public ProductNotFoundException(String title) {

        super("Product with title: "+title+" not available in the store!!");
    }
}
