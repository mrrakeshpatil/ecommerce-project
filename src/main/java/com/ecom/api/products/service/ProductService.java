package com.ecom.api.products.service;

import com.ecom.api.products.repository.CategoryRepository;
import com.ecom.api.products.repository.ProductRepository;
import com.ecom.api.products.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service


public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    public Product createProduct(Product product, Long categoryId) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

        public Page<Product> getProductWithPaginationAndSorting(int pageNumber, int pageSize, String field){
        return productRepository.findAll(PageRequest.of(pageNumber,pageSize).withSort(Sort.by(Sort.Direction.ASC,field)));
    }



}
