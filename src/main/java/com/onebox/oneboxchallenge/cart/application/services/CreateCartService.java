package com.onebox.oneboxchallenge.cart.application.services;

import com.onebox.oneboxchallenge.cart.application.ports.input.CreateCartUseCase;
import com.onebox.oneboxchallenge.cart.application.ports.output.CartRepositoryPort;
import com.onebox.oneboxchallenge.cart.domain.model.Cart;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCartService implements CreateCartUseCase {

    private final CartRepositoryPort cartRepository;

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }
}
