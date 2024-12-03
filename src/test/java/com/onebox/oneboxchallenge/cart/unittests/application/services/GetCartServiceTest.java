package com.onebox.oneboxchallenge.cart.unittests.application.services;

import com.onebox.oneboxchallenge.cart.application.ports.output.CartRepositoryPort;
import com.onebox.oneboxchallenge.cart.application.services.GetCartService;
import com.onebox.oneboxchallenge.cart.domain.exceptions.CartNotFoundException;
import com.onebox.oneboxchallenge.cart.domain.model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetCartServiceTest {

    @Mock
    private CartRepositoryPort cartRepository;

    @InjectMocks
    private GetCartService getCartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetCartByIdSuccessfully() {
        // Arrange
        Long cartId = 1L;
        Cart cart = new Cart();
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

        // Act
        Cart result = getCartService.getCartById(cartId);

        // Assert
        assertEquals(cart, result);
        verify(cartRepository).findById(cartId);
    }

    @Test
    void shouldThrowExceptionWhenCartNotFound() {
        // Arrange
        Long cartId = 1L;
        when(cartRepository.findById(cartId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                CartNotFoundException.class,
                () -> getCartService.getCartById(cartId)
        );

        verify(cartRepository).findById(cartId);
    }
}
