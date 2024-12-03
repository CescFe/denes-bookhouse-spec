package com.onebox.oneboxchallenge.cart.application.services;

import com.onebox.oneboxchallenge.cart.application.ports.input.DeleteCartUseCase;
import com.onebox.oneboxchallenge.cart.application.ports.output.CartRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteCartService implements DeleteCartUseCase {

    private final CartRepositoryPort cartRepository;

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
