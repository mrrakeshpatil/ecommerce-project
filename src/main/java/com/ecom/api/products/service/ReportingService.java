package com.ecom.api.products.service;

import com.ecom.api.products.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportingService {

    @Autowired
    private SaleRepository saleRepository;

    public List<Object[]> getTopSellingProducts(int topN) {
        return saleRepository.findTopSellingProducts(PageRequest.of(0, topN));
    }

    public List<Object[]> getSalesByCategory() {
        return saleRepository.findSalesByCategory();
    }
}
