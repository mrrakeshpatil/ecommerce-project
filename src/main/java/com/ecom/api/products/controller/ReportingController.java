package com.ecom.api.products.controller;

import com.ecom.api.products.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {

    @Autowired
    private ReportingService reportingService;

    @GetMapping("/top-selling-products")
    public ResponseEntity<List<Object[]>> getTopSellingProducts(@RequestParam int topN) {
        return ResponseEntity.ok(reportingService.getTopSellingProducts(topN));
    }

    @GetMapping("/sales-by-category")
    public ResponseEntity<List<Object[]>> getSalesByCategory() {
        return ResponseEntity.ok(reportingService.getSalesByCategory());
    }
}
