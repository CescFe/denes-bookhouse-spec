package com.onebox.oneboxchallenge.product.unittests.application.services;

import com.onebox.oneboxchallenge.product.application.ports.output.ProductRepositoryPort;
import com.onebox.oneboxchallenge.product.application.services.CreateProductService;
import com.onebox.oneboxchallenge.product.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateProductServiceTest {
    @Mock
    private ProductRepositoryPort productRepository;

    @InjectMocks
    private CreateProductService createProductService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateProductSuccessfully() {
        // Arrange
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product createdProduct = createProductService.createProduct(product);

        // Assert
        assertEquals(product, createdProduct);
        verify(productRepository).save(product);
    }
}
