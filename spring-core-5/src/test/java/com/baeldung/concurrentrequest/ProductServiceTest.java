package com.baeldung.concurrentrequest;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    void getProductById() {
        ProductService<Stock> productService = new ProductService<>();
        Optional<Product<Stock>> actual = productService.getProductById(0);
        assertEquals(Optional.class, actual.getClass());
    }
}