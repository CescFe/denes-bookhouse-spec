package com.onebox.oneboxchallenge.cart.application.ports.input;

import com.onebox.oneboxchallenge.cart.domain.model.Cart;

public interface GetCartUseCase {
    Cart getCartById(Long id);
}
