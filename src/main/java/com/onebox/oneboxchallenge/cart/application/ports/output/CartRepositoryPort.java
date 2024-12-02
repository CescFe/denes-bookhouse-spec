package com.onebox.oneboxchallenge.cart.application.ports.output;

import com.onebox.oneboxchallenge.cart.domain.model.Cart;

import java.util.Optional;

public interface CartRepositoryPort {
    Cart save(Cart cart);
    Optional<Cart> findById(Long id);
    void deleteById(Long id);
}
