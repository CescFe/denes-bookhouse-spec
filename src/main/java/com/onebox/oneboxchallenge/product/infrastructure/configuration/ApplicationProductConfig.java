package com.onebox.oneboxchallenge.product.infrastructure.configuration;

import com.onebox.oneboxchallenge.product.application.ports.input.CreateProductUseCase;
import com.onebox.oneboxchallenge.product.application.ports.input.DeleteProductUseCase;
import com.onebox.oneboxchallenge.product.application.ports.input.GetAllProductsUseCase;
import com.onebox.oneboxchallenge.product.application.ports.input.GetProductUseCase;
import com.onebox.oneboxchallenge.product.application.ports.input.UpdateProductUseCase;
import com.onebox.oneboxchallenge.product.application.ports.output.ProductRepositoryPort;
import com.onebox.oneboxchallenge.product.application.services.CreateProductService;
import com.onebox.oneboxchallenge.product.application.services.DeleteProductService;
import com.onebox.oneboxchallenge.product.application.services.GetAllProductsService;
import com.onebox.oneboxchallenge.product.application.services.GetProductService;
import com.onebox.oneboxchallenge.product.application.services.UpdateProductService;
import com.onebox.oneboxchallenge.product.infrastructure.adapters.output.JpaProductRepository;
import com.onebox.oneboxchallenge.product.infrastructure.repository.SpringDataProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationProductConfig {

    @Bean
    public ProductRepositoryPort productRepositoryPort(SpringDataProductRepository repository) {
        return new JpaProductRepository(repository);
    }

    @Bean
    public CreateProductUseCase createProductUseCase(ProductRepositoryPort productRepositoryPort) {
        return new CreateProductService(productRepositoryPort);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase(ProductRepositoryPort productRepositoryPort) {
        return new DeleteProductService(productRepositoryPort);
    }

    @Bean
    public GetAllProductsUseCase getAllProductsUseCase(ProductRepositoryPort productRepositoryPort) {
        return new GetAllProductsService(productRepositoryPort);
    }

    @Bean
    public GetProductUseCase getProductUseCase(ProductRepositoryPort productRepositoryPort) {
        return new GetProductService(productRepositoryPort);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase(ProductRepositoryPort productRepositoryPort) {
        return new UpdateProductService(productRepositoryPort);
    }
}
