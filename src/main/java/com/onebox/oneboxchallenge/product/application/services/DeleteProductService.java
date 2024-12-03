package com.onebox.oneboxchallenge.product.application.services;

import com.onebox.oneboxchallenge.product.application.ports.input.DeleteProductUseCase;
import com.onebox.oneboxchallenge.product.application.ports.output.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteProductService implements DeleteProductUseCase {

    private final ProductRepositoryPort productRepository;

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
