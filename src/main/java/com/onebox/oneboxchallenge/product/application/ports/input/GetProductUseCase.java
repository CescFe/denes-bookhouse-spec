package com.onebox.oneboxchallenge.product.application.ports.input;

import com.onebox.oneboxchallenge.product.domain.model.Product;

public interface GetProductUseCase {
    Product getProductById(Long id);
}
