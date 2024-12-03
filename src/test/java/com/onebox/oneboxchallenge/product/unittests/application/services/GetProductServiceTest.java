package com.onebox.oneboxchallenge.product.unittests.application.services;

import com.onebox.oneboxchallenge.product.application.ports.output.ProductRepositoryPort;
import com.onebox.oneboxchallenge.product.application.services.GetProductService;
import com.onebox.oneboxchallenge.product.domain.exceptions.ProductNotFoundException;
import com.onebox.oneboxchallenge.product.domain.model.Product;
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

public class GetProductServiceTest {

    @Mock
    private ProductRepositoryPort productRepository;

    @InjectMocks
    private GetProductService getProductService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetProductSuccessfully() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        Product result = getProductService.getProductById(productId);

        // Assert
        assertEquals(product, result);
        verify(productRepository).findById(productId);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        // Arrange
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ProductNotFoundException.class, () -> getProductService.getProductById(productId));
        verify(productRepository).findById(productId);
    }
}
