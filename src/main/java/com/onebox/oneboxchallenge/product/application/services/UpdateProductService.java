package com.onebox.oneboxchallenge.product.application.services;

import com.onebox.oneboxchallenge.product.application.ports.input.UpdateProductUseCase;
import com.onebox.oneboxchallenge.product.application.ports.output.ProductRepositoryPort;
import com.onebox.oneboxchallenge.product.domain.model.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateProductService implements UpdateProductUseCase {

    private final ProductRepositoryPort productRepository;

    @Override
    public Product updateProduct(Long id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }
}
