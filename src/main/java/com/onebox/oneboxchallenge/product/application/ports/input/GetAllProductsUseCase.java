package com.onebox.oneboxchallenge.product.application.ports.input;

import com.onebox.oneboxchallenge.product.domain.model.Product;

import java.util.List;

public interface GetAllProductsUseCase {
    List<Product> getAllProducts();
}
