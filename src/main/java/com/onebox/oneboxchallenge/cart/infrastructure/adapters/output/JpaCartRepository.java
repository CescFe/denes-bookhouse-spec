package com.onebox.oneboxchallenge.cart.infrastructure.adapters.output;

import com.onebox.oneboxchallenge.cart.application.ports.output.CartRepositoryPort;
import com.onebox.oneboxchallenge.cart.domain.model.Cart;
import com.onebox.oneboxchallenge.cart.infrastructure.entity.CartEntity;
import com.onebox.oneboxchallenge.cart.infrastructure.repository.SpringDataCartRepository;
import com.onebox.oneboxchallenge.product.domain.model.Product;
import com.onebox.oneboxchallenge.product.infrastructure.entity.ProductEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JpaCartRepository implements CartRepositoryPort {

    private final SpringDataCartRepository repository;

    @Override
    public Cart save(Cart cart) {
        CartEntity entity = mapToEntity(cart);
        return mapToDomain(repository.save(entity));
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return repository.findById(id).map(this::mapToDomain);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private CartEntity mapToEntity(Cart cart) {
        CartEntity entity = new CartEntity();
        entity.setId(cart.getId());
        entity.setProducts(cart.getProducts().stream()
                .map(this::mapProductToEntity)
                .collect(Collectors.toList()));
        return entity;
    }

    private Cart mapToDomain(CartEntity entity) {
        return new Cart(entity.getId(), entity.getProducts().stream()
                .map(this::mapProductToDomain)
                .collect(Collectors.toList()));
    }

    private ProductEntity mapProductToEntity(Product product) {
        return new ProductEntity(product.getId(), product.getDescription(), product.getAmount());
    }

    private Product mapProductToDomain(ProductEntity entity) {
        return new Product(entity.getId(), entity.getDescription(), entity.getAmount());
    }
}
