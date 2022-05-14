package com.baeldung.concurrentrequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController()
@RequestMapping("product")
public class ProductController implements IProductController<Stock<Object>> {

    private final ProductService<Stock<Object>> productService;

    public ProductController(ProductService<Stock<Object>> productService) {
        this.productService = Objects.requireNonNull(productService);
    }

    @Override
    @GetMapping("/{id}")
    public Product<Stock<Object>> getProductDetails(@PathVariable("id") int productId) {
        return productService.getProductById(productId)
          .orElse(null);
    }

    @Override
    @GetMapping("{id}/stock")
    public Stock<Object> getProductStock(@PathVariable("id") int productId) {
        return productService.getProductById(productId)
          .map(Product::get)
          .orElse(null);
    }
}
