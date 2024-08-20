package com.ecom.api.products.repository;

import com.ecom.api.products.model.Sale;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
    @Query(value = "SELECT s.product, SUM(s.quantity) AS totalQuantity FROM Sale s GROUP BY s.product ORDER BY totalQuantity DESC",nativeQuery = true)
    List<Object[]> findTopSellingProducts(Pageable pageable);

    @Query(value = "SELECT p.category, SUM(s.quantity * p.price) AS totalSales FROM Sale s JOIN s.product p GROUP BY p.category",nativeQuery = true)
    List<Object[]> findSalesByCategory();

}
