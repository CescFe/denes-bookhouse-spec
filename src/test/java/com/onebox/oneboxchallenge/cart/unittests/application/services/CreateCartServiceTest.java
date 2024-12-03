package com.onebox.oneboxchallenge.cart.unittests.application.services;

import com.onebox.oneboxchallenge.cart.application.ports.output.CartRepositoryPort;
import com.onebox.oneboxchallenge.cart.application.services.CreateCartService;
import com.onebox.oneboxchallenge.cart.domain.model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateCartServiceTest {

    @Mock
    private CartRepositoryPort cartRepository;

    @InjectMocks
    private CreateCartService createCartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCartSuccessfully() {
        // Arrange
        Cart cart = new Cart();
        when(cartRepository.save(cart)).thenReturn(cart);

        // Act
        Cart createdCart = createCartService.createCart(cart);

        // Assert
        assertEquals(cart, createdCart);
        verify(cartRepository).save(cart);
    }
}
