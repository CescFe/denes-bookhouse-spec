package com.onebox.oneboxchallenge.cart.application.services;

import com.onebox.oneboxchallenge.cart.application.ports.input.CartUseCase;
import com.onebox.oneboxchallenge.cart.application.ports.output.CartRepositoryPort;
import com.onebox.oneboxchallenge.cart.domain.exceptions.CartNotFoundException;
import com.onebox.oneboxchallenge.cart.domain.model.Cart;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartService implements CartUseCase {

    public static final String CART_NOT_FOUND = "Cart not found";
    private final CartRepositoryPort cartRepository;

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCart(Long id, Cart cart) {
        cart.setId(id);
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException(CART_NOT_FOUND));
    }
}
