package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping("public/categories")
    public ResponseEntity<List<Category>> getAllCategories()    {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllCategories());
    }

    @PostMapping("public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
        service.createCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body("Category added successfully");
    }

    @DeleteMapping("public/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@Valid @PathVariable Long categoryId) {
        String status = service.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(status);

    }

    @PutMapping("public/update/{categoryId}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody Category category, @PathVariable Long categoryId){
        Category responseCategory = service.updateCategory(category, categoryId);
        return ResponseEntity.ok(responseCategory.toString());
    }
}
