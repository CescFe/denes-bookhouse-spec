package com.onebox.oneboxchallenge.product.infrastructure.adapters.output;

import com.onebox.oneboxchallenge.product.application.ports.output.ProductRepositoryPort;
import com.onebox.oneboxchallenge.product.domain.model.Product;
import com.onebox.oneboxchallenge.product.infrastructure.entity.ProductEntity;
import com.onebox.oneboxchallenge.product.infrastructure.repository.SpringDataProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JpaProductRepository implements ProductRepositoryPort {

    private final SpringDataProductRepository repository;

    @Override
    public Product save(Product product) {
        ProductEntity entity = mapToEntity(product);
        return mapToDomain(repository.save(entity));
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id).map(this::mapToDomain);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(this::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private ProductEntity mapToEntity(Product product) {
        return new ProductEntity(product.getId(), product.getDescription(), product.getAmount());
    }

    private Product mapToDomain(ProductEntity entity) {
        return new Product(entity.getId(), entity.getDescription(), entity.getAmount());
    }
}
