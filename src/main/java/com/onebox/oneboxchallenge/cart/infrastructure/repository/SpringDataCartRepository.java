package com.onebox.oneboxchallenge.cart.infrastructure.repository;

import com.onebox.oneboxchallenge.cart.infrastructure.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCartRepository extends JpaRepository<CartEntity, Long> {
}
