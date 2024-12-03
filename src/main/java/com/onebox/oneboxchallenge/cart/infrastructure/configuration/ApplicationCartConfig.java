package com.onebox.oneboxchallenge.cart.infrastructure.configuration;

import com.onebox.oneboxchallenge.cart.application.ports.input.CreateCartUseCase;
import com.onebox.oneboxchallenge.cart.application.ports.input.DeleteCartUseCase;
import com.onebox.oneboxchallenge.cart.application.ports.input.GetCartUseCase;
import com.onebox.oneboxchallenge.cart.application.ports.input.UpdateCartUseCase;
import com.onebox.oneboxchallenge.cart.application.ports.output.CartRepositoryPort;
import com.onebox.oneboxchallenge.cart.application.services.CreateCartService;
import com.onebox.oneboxchallenge.cart.application.services.DeleteCartService;
import com.onebox.oneboxchallenge.cart.application.services.GetCartService;
import com.onebox.oneboxchallenge.cart.application.services.UpdateCartService;
import com.onebox.oneboxchallenge.cart.infrastructure.adapters.output.JpaCartRepository;
import com.onebox.oneboxchallenge.cart.infrastructure.repository.SpringDataCartRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationCartConfig {

    @Bean
    public CartRepositoryPort cartRepositoryPort(SpringDataCartRepository repository) {
        return new JpaCartRepository(repository);
    }

    @Bean
    public CreateCartUseCase createCartUseCase(CartRepositoryPort cartRepositoryPort) {
        return new CreateCartService(cartRepositoryPort);
    }

    @Bean
    public UpdateCartUseCase updateCartUseCase(CartRepositoryPort cartRepositoryPort) {
        return new UpdateCartService(cartRepositoryPort);
    }

    @Bean
    public DeleteCartUseCase deleteCartUseCase(CartRepositoryPort cartRepositoryPort) {
        return new DeleteCartService(cartRepositoryPort);
    }

    @Bean
    public GetCartUseCase getCartByIdUseCase(CartRepositoryPort cartRepositoryPort) {
        return new GetCartService(cartRepositoryPort);
    }

}
