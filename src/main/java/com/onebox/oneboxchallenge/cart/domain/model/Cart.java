package com.onebox.oneboxchallenge.cart.domain.model;

import com.onebox.oneboxchallenge.product.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Long id;
    private List<Product> products;
}
