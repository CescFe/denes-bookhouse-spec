package com.onebox.oneboxchallenge.product.application.ports.output;

import com.onebox.oneboxchallenge.product.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
}
