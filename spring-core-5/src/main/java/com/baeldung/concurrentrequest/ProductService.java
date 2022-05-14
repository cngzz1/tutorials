package com.baeldung.concurrentrequest;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService<E> {
    E productType;
    // @formatter:off
    private static final List<Product<Stock<Object>>> productRepository = Arrays.asList(
            new Product<>(1, "Product 1", new Stock<>(100)),
            new Product<>(2, "Product 2", new Stock<>(50))
    );
    // @formatter:on

    public Optional<Product<Stock<Object>>> getProductById(int id) {
        for (Product<Stock<Object>> p : productRepository) {
            if (p.getId() == id) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }
    public E get(){
        return productType;
    }
}
