package com.ecom.api.products.repository;

import com.ecom.api.products.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
