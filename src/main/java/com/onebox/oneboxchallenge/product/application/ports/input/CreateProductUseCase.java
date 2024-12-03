package com.onebox.oneboxchallenge.product.application.ports.input;

import com.onebox.oneboxchallenge.product.domain.model.Product;

public interface CreateProductUseCase {
    Product createProduct(Product product);
}
