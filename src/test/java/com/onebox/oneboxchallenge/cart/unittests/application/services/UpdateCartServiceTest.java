package com.onebox.oneboxchallenge.cart.unittests.application.services;

import com.onebox.oneboxchallenge.cart.application.ports.output.CartRepositoryPort;
import com.onebox.oneboxchallenge.cart.application.services.UpdateCartService;
import com.onebox.oneboxchallenge.cart.domain.model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateCartServiceTest {

    @Mock
    private CartRepositoryPort cartRepository;

    @InjectMocks
    private UpdateCartService updateCartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateCartSuccessfully() {
        // Arrange
        Long cartId = 1L;
        Cart cart = new Cart();
        Cart updatedCart = new Cart();
        updatedCart.setId(cartId);

        when(cartRepository.save(cart)).thenReturn(updatedCart);

        // Act
        Cart result = updateCartService.updateCart(cartId, cart);

        // Assert
        assertEquals(cartId, result.getId());
        verify(cartRepository).save(cart);
    }
}
