package com.onebox.oneboxchallenge.product.infrastructure.configuration;

import com.onebox.oneboxchallenge.product.application.ports.output.ProductRepositoryPort;
import com.onebox.oneboxchallenge.product.application.services.ProductService;
import com.onebox.oneboxchallenge.product.infrastructure.adapters.output.JpaProductRepository;
import com.onebox.oneboxchallenge.product.infrastructure.repository.SpringDataProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ProductService productService(ProductRepositoryPort productRepositoryPort) {
        return new ProductService(productRepositoryPort);
    }

    @Bean
    public ProductRepositoryPort productRepositoryPort(SpringDataProductRepository repository) {
        return new JpaProductRepository(repository);
    }
}
