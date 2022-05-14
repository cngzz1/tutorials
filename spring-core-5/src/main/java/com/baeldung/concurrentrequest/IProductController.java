package com.baeldung.concurrentrequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IProductController<E> {
    @GetMapping("/{id}")
    Product<E> getProductDetails(@PathVariable("id") int productId);

    @GetMapping("{id}/stock")
    E getProductStock(@PathVariable("id") int productId);
}
