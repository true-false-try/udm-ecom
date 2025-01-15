package com.ecommerce.project.controller;

import com.ecommerce.project.payload.CategoryDto;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ecommerce.project.config.AppConstants.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping("public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name = "pageNumber", defaultValue = PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = SORT_CATEGORIES_BY) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = SORT_DIR) String sortOrder
    )    {
        return ResponseEntity.ok(service.getAllCategories(pageNumber, pageSize, sortBy, sortOrder));
    }

    @PostMapping("public/categories")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity(service.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @DeleteMapping("public/categories/{categoryId}")
    public ResponseEntity<CategoryDto> deleteCategory(@Valid @PathVariable Long categoryId) {
        return ResponseEntity.ok(service.deleteCategory(categoryId));

    }

    @PutMapping("public/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto category, @PathVariable Long categoryId){
        return ResponseEntity.ok(service.updateCategory(category, categoryId));
    }
}
