package com.onebox.oneboxchallenge.cart.application.services;

import com.onebox.oneboxchallenge.cart.application.ports.input.GetCartUseCase;
import com.onebox.oneboxchallenge.cart.application.ports.output.CartRepositoryPort;
import com.onebox.oneboxchallenge.cart.domain.exceptions.CartNotFoundException;
import com.onebox.oneboxchallenge.cart.domain.model.Cart;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetCartService implements GetCartUseCase {

    private final CartRepositoryPort cartRepository;
    private static final String CART_NOT_FOUND = "Cart not found";

    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException(CART_NOT_FOUND));
    }
}
