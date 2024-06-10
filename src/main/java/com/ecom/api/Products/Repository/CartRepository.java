package com.ecom.api.Products.Repository;

import com.ecom.api.Products.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
