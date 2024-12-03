package com.onebox.oneboxchallenge.product.infrastructure.adapters.input;

import com.onebox.oneboxchallenge.product.application.ports.input.CreateProductUseCase;
import com.onebox.oneboxchallenge.product.application.ports.input.DeleteProductUseCase;
import com.onebox.oneboxchallenge.product.application.ports.input.GetAllProductsUseCase;
import com.onebox.oneboxchallenge.product.application.ports.input.GetProductUseCase;
import com.onebox.oneboxchallenge.product.application.ports.input.UpdateProductUseCase;
import com.onebox.oneboxchallenge.product.domain.model.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ProductsApi;
import org.openapitools.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductsApi {

    private final CreateProductUseCase createProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;

    @Override
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product product = mapToDomain(productDTO);
        Product createdProduct = createProductUseCase.createProduct(product);
        ProductDTO responseDTO = mapToDto(createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Override
    public ResponseEntity<ProductDTO> getProductById(Long id) {
        Product product = getProductUseCase.getProductById(id);
        return ResponseEntity.ok(mapToDto(product));
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = getAllProductsUseCase.getAllProducts();
        List<ProductDTO> productDTOs = products.stream().map(this::mapToDto).collect(Collectors.toList());
        return ResponseEntity.ok(productDTOs);
    }

    @Override
    public ResponseEntity<ProductDTO> updateProduct(Long id, ProductDTO productDTO) {
        Product product = mapToDomain(productDTO);
        Product updatedProduct = updateProductUseCase.updateProduct(id, product);
        return ResponseEntity.ok(mapToDto(updatedProduct));
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long id) {
        deleteProductUseCase.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    private Product mapToDomain(ProductDTO dto) {
        return new Product(dto.getId(), dto.getDescription(), dto.getAmount());
    }

    private ProductDTO mapToDto(Product domain) {
        return new ProductDTO().id(domain.getId()).description(domain.getDescription()).amount(domain.getAmount());
    }
}
