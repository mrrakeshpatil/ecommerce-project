package com.ecom.api.Products.Repository;

import com.ecom.api.Products.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
