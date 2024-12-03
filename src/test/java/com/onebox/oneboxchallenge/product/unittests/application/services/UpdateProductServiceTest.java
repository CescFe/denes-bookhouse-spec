package com.onebox.oneboxchallenge.product.unittests.application.services;

import com.onebox.oneboxchallenge.product.application.ports.output.ProductRepositoryPort;
import com.onebox.oneboxchallenge.product.application.services.UpdateProductService;
import com.onebox.oneboxchallenge.product.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateProductServiceTest {

    public static final long PRODUCT_ID = 1L;
    public static final String DESCRIPTION_OLD = "Old Product";
    public static final long AMOUNT_OLD = 10L;
    public static final String DESCRIPTION_UPDATED = "Updated Product";
    public static final long AMOUNT_UPDATED = 20L;

    @Mock
    private ProductRepositoryPort productRepository;

    @InjectMocks
    private UpdateProductService updateProductService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateProductSuccessfully() {
        // Arrange
        Long productId = PRODUCT_ID;
        Product product = new Product();
        product.setDescription(DESCRIPTION_OLD);
        product.setAmount(AMOUNT_OLD);
        Product updatedProduct = new Product();
        updatedProduct.setId(productId);
        updatedProduct.setDescription(DESCRIPTION_UPDATED);
        updatedProduct.setAmount(AMOUNT_UPDATED);

        when(productRepository.save(product)).thenReturn(updatedProduct);

        // Act
        Product result = updateProductService.updateProduct(productId, product);

        // Assert
        assertEquals(productId, result.getId());
        assertEquals(DESCRIPTION_UPDATED, result.getDescription());
        assertEquals(AMOUNT_UPDATED, result.getAmount());
        verify(productRepository).save(product);
    }
}
