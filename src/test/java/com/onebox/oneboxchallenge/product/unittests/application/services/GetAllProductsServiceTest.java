package com.onebox.oneboxchallenge.product.unittests.application.services;

import com.onebox.oneboxchallenge.product.application.ports.output.ProductRepositoryPort;
import com.onebox.oneboxchallenge.product.application.services.GetAllProductsService;
import com.onebox.oneboxchallenge.product.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllProductsServiceTest {

    @Mock
    private ProductRepositoryPort productRepository;

    @InjectMocks
    private GetAllProductsService getAllProductsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllProductsSuccessfully() {
        // Arrange
        List<Product> products = Arrays.asList(
                new Product(),
                new Product()
        );
        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<Product> result = getAllProductsService.getAllProducts();

        // Assert
        assertEquals(products, result);
        verify(productRepository).findAll();
    }
}
