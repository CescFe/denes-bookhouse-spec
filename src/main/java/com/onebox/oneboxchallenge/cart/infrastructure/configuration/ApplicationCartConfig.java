package com.onebox.oneboxchallenge.cart.infrastructure.configuration;

import com.onebox.oneboxchallenge.cart.application.ports.output.CartRepositoryPort;
import com.onebox.oneboxchallenge.cart.application.services.CartService;
import com.onebox.oneboxchallenge.cart.infrastructure.adapters.output.JpaCartRepository;
import com.onebox.oneboxchallenge.cart.infrastructure.repository.SpringDataCartRepository;
import com.onebox.oneboxchallenge.product.infrastructure.repository.SpringDataProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationCartConfig {

    @Bean
    public CartService cartService(CartRepositoryPort cartRepositoryPort) {
        return new CartService(cartRepositoryPort);
    }

    @Bean
    public CartRepositoryPort cartRepositoryPort(SpringDataCartRepository repository) {
        return new JpaCartRepository(repository);
    }
}
