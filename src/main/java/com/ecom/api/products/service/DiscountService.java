package com.ecom.api.products.service;

import com.ecom.api.products.model.Discount;
import com.ecom.api.products.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepository;

    public Discount createDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    public Optional<Discount> getDiscountById(Long id) {
        return discountRepository.findById(id);
    }

    public Discount updateDiscount(Long id, Discount discountDetails) {
        Discount discount = discountRepository.findById(id).orElseThrow(() -> new RuntimeException("Discount not found"));
        discount.setCode(discountDetails.getCode());
        discount.setPercentage(discountDetails.getPercentage());
        discount.setValidFrom(discountDetails.getValidFrom());
        discount.setValidTo(discountDetails.getValidTo());
        return discountRepository.save(discount);
    }

    public void deleteDiscount(Long id) {
        Discount discount = discountRepository.findById(id).orElseThrow(() -> new RuntimeException("Discount not found"));
        discountRepository.delete(discount);
    }

    public Optional<List<Discount>> findByCode(String code) {
        return discountRepository.findByCode(code);
    }
}
