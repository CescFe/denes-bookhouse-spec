package com.onebox.oneboxchallenge.cart.unittests.application.services;

import com.onebox.oneboxchallenge.cart.application.ports.output.CartRepositoryPort;
import com.onebox.oneboxchallenge.cart.application.services.DeleteCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class DeleteCartServiceTest {

    @Mock
    private CartRepositoryPort cartRepository;

    @InjectMocks
    private DeleteCartService deleteCartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDeleteCartSuccessfully() {
        // Arrange
        Long cartId = 1L;
        doNothing().when(cartRepository).deleteById(cartId);

        // Act
        deleteCartService.deleteCart(cartId);

        // Assert
        verify(cartRepository).deleteById(cartId);
    }
}
