package com.onebox.oneboxchallenge.cart.application.services;

import com.onebox.oneboxchallenge.cart.application.ports.input.UpdateCartUseCase;
import com.onebox.oneboxchallenge.cart.application.ports.output.CartRepositoryPort;
import com.onebox.oneboxchallenge.cart.domain.model.Cart;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateCartService implements UpdateCartUseCase {

    private final CartRepositoryPort cartRepository;

    @Override
    public Cart updateCart(Long id, Cart cart) {
        cart.setId(id);
        return cartRepository.save(cart);
    }
}
