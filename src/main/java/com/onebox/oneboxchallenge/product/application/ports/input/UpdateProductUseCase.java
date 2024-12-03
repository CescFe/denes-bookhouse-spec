package com.onebox.oneboxchallenge.product.application.ports.input;

import com.onebox.oneboxchallenge.product.domain.model.Product;

public interface UpdateProductUseCase {
    Product updateProduct(Long id, Product product);
}
