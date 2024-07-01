package com.ecom.api.products.controller;

import com.ecom.api.products.model.Category;
import com.ecom.api.products.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//1. Create a new entity called "Category" with fields: id, name, description
//Implement CRUD operations for categories:
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
//- POST /api/categories: Create a new category
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
//- GET /api/categories: Retrieve all categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    //- GET /api/categories/{id}: Retrieve a specific category
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
//- PUT /api/categories/{id}: Update a category
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(categoryDetails.getName());
                    category.setDescription(categoryDetails.getDescription());
                    return ResponseEntity.ok(categoryRepository.save(category));
                }).orElse(ResponseEntity.notFound().build());
    }
//- DELETE /api/categories/{id}: Delete a category
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
