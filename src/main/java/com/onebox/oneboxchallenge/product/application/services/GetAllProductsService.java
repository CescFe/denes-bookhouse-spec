package com.onebox.oneboxchallenge.product.application.services;

import com.onebox.oneboxchallenge.product.application.ports.input.GetAllProductsUseCase;
import com.onebox.oneboxchallenge.product.application.ports.output.ProductRepositoryPort;
import com.onebox.oneboxchallenge.product.domain.model.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllProductsService implements GetAllProductsUseCase {

    private final ProductRepositoryPort productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
