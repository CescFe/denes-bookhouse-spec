package com.onebox.oneboxchallenge.cart.unittests.application.services;

import com.onebox.oneboxchallenge.cart.application.ports.output.CartRepositoryPort;
import com.onebox.oneboxchallenge.cart.application.services.UpdateCartService;
import com.onebox.oneboxchallenge.cart.domain.model.Cart;
import com.onebox.oneboxchallenge.product.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateCartServiceTest {

    private static final Long CART_ID = 1L;
    private static final Long PRODUCT1_ID = 1L;
    private static final String PRODUCT1_DESCRIPTION = "Product1";
    public static final long PRODUCT1_AMOUNT = 10L;
    private static final String PRODUCT1_DESCRIPTION_UPDATED = "Product1 Updated";
    private static final long PRODUCT1_AMOUNT_UPDATED = 20L;

    private static final Long PRODUCT2_ID = 2L;
    private static final String PRODUCT2_DESCRIPTION = "Product2";
    private static final long PRODUCT2_AMOUNT = 5L;

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

        // Original cart to be updated
        Cart cart = new Cart();
        cart.setId(CART_ID);
        cart.setProducts(List.of(new Product(PRODUCT1_ID, PRODUCT1_DESCRIPTION, PRODUCT1_AMOUNT)));

        // Updated cart returned by the repository
        Cart updatedCart = new Cart();
        updatedCart.setId(CART_ID);
        updatedCart.setProducts(List.of(
                new Product(PRODUCT1_ID, PRODUCT1_DESCRIPTION_UPDATED, PRODUCT1_AMOUNT_UPDATED), // Updated quantity for existing product
                new Product(PRODUCT2_ID, PRODUCT2_DESCRIPTION, PRODUCT2_AMOUNT)   // New product added
        ));

        when(cartRepository.save(cart)).thenReturn(updatedCart);

        // Act
        Cart result = updateCartService.updateCart(CART_ID, cart);

        // Assert
        assertEquals(CART_ID, result.getId());
        assertEquals(2, result.getProducts().size());

        // Check first product
        assertEquals(PRODUCT1_ID, result.getProducts().getFirst().getId());
        assertEquals(PRODUCT1_DESCRIPTION_UPDATED, result.getProducts().getFirst().getDescription());
        assertEquals(PRODUCT1_AMOUNT_UPDATED, result.getProducts().getFirst().getAmount());

        // Check last product
        assertEquals(PRODUCT2_ID, result.getProducts().getLast().getId());
        assertEquals(PRODUCT2_DESCRIPTION, result.getProducts().getLast().getDescription());
        assertEquals(PRODUCT2_AMOUNT, result.getProducts().getLast().getAmount());

        verify(cartRepository).save(cart);
    }
}
