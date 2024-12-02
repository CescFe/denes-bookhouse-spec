package com.onebox.oneboxchallenge.product.infrastructure.repository;

import com.onebox.oneboxchallenge.product.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductRepository extends JpaRepository<ProductEntity, Long> {
}
