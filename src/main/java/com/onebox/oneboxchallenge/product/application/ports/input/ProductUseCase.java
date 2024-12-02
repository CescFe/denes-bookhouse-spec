package com.onebox.oneboxchallenge.product.application.ports.input;

import com.onebox.oneboxchallenge.product.domain.model.Product;

import java.util.List;

public interface ProductUseCase {
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    Product getProductById(Long id);
    List<Product> getAllProducts();
}
