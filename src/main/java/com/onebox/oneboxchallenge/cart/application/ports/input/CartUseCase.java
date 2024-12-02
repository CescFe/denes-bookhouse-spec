package com.onebox.oneboxchallenge.cart.application.ports.input;

import com.onebox.oneboxchallenge.cart.domain.model.Cart;

public interface CartUseCase {
    Cart createCart(Cart cart);
    Cart updateCart(Long id, Cart cart);
    void deleteCart(Long id);
    Cart getCartById(Long id);
}
