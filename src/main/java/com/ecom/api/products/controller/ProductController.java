package com.ecom.api.products.controller;

import com.ecom.api.products.model.Product;
import com.ecom.api.products.repository.CategoryRepository;
import com.ecom.api.products.repository.ProductRepository;
import com.ecom.api.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private  ProductRepository productRepository;

    @Autowired
    private  CategoryRepository categoryRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


    //-- Update GET /api/products to include category details in the response
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Product createProducts(@RequestBody Product product) {
        return productService.save(product);
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return categoryRepository.findById(product.getId())
                .map(category -> {
                    product.setCategory(category);
                    return ResponseEntity.ok(productRepository.save(product));
                }).orElse(ResponseEntity.badRequest().build());
    }

    //-- GET /api/products/search?query={search_term}: Search products by name or description
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String query) {
        return productRepository.findByNameContainingOrDescriptionContaining(query, query);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDetails.getName());
                    product.setPrice(productDetails.getPrice());
                    if (productDetails.getCategory() != null) {
                        categoryRepository.findById(productDetails.getCategory().getId())
                                .ifPresent(product::setCategory);
                    }
                    return ResponseEntity.ok(productRepository.save(product));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id, @RequestBody Product productDetails) {

        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    //- GET /api/products?category_id={category_id}: Retrieve products filtered by category
    @GetMapping(params = "category_id")
    public List<Product> getProductsByCategoryId(@RequestParam("category_id") Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    //5. Pagination and Sorting:
    //- Implement pagination and sorting for product listing:
    //-- GET /api/products?page={page_number}&size={page_size}&sort={sort_field},{sort_direction}


        @GetMapping("/page")
    public Page<Product> getProductsPage(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortField,
            @RequestParam String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findAll(pageable);
    }







}
