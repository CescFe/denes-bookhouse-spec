package com.onebox.oneboxchallenge.product.application.services;

import com.onebox.oneboxchallenge.product.application.ports.input.CreateProductUseCase;
import com.onebox.oneboxchallenge.product.application.ports.output.ProductRepositoryPort;
import com.onebox.oneboxchallenge.product.domain.model.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateProductService implements CreateProductUseCase {

    private final ProductRepositoryPort productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
