package com.ecommerce.project.service;

import com.ecommerce.project.payload.CategoryDto;
import com.ecommerce.project.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto deleteCategory(Long categoryId);
    CategoryDto updateCategory(CategoryDto category, Long categoryId);
}
