package com.ecom.api.products.repository;

import com.ecom.api.products.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount,Long> {
    Optional<List<Discount>> findByCode(String code);
}
