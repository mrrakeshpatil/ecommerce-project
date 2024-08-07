package com.ecom.api.products.controller;

import com.ecom.api.products.model.Discount;
import com.ecom.api.products.repository.DiscountRepository;
import com.ecom.api.products.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {
    @Autowired
    private DiscountRepository discountRepository;

    @PostMapping
    public ResponseEntity<Discount> createDiscount(@RequestBody Discount discount) {
        Discount savedDiscount = discountRepository.save(discount);
        return ResponseEntity.ok(savedDiscount);
    }

    @GetMapping
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discount> getDiscountById(@PathVariable Long id) {
        Optional<Discount> discount = discountRepository.findById(id);
        if (discount.isPresent()) {
            return ResponseEntity.ok(discount.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discount> updateDiscount(@PathVariable Long id, @RequestBody Discount discountDetails) {
        Optional<Discount> discount = discountRepository.findById(id);
        if (discount.isPresent()) {
            Discount updatedDiscount = discount.get();
            updatedDiscount.setCode(discountDetails.getCode());
            updatedDiscount.setPercentage(discountDetails.getPercentage());
            updatedDiscount.setValidFrom(discountDetails.getValidFrom());
            updatedDiscount.setValidTo(discountDetails.getValidTo());
            discountRepository.save(updatedDiscount);
            return ResponseEntity.ok(updatedDiscount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable Long id) {
        Optional<Discount> discount = discountRepository.findById(id);
        if (discount.isPresent()) {
            discountRepository.delete(discount.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
