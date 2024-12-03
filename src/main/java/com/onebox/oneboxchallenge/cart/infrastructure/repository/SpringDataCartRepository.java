package com.onebox.oneboxchallenge.cart.infrastructure.repository;

import com.onebox.oneboxchallenge.cart.infrastructure.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface SpringDataCartRepository extends JpaRepository<CartEntity, Long> {
    void deleteByUpdatedAtBefore(LocalDateTime timestamp);
}
