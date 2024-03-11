package com.simplestore.productservice.repositories;

public interface CustomQueries {
    String updateProductPrice = "update product set price_id = UUID_TO_BIN(:priceID) where Id = UUID_TO_BIN(:id)";
}
