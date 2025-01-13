package com.ecommerce.project.service.impl;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import com.ecommerce.project.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
        public String deleteCategory(Long categoryId) throws ResponseStatusException {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() ->  new ResponseStatusException(NOT_FOUND, "Resource not found."));
            categoryRepository.deleteById(categoryId);
            return category.toString();
        }

    @Override
    public Category updateCategory(Category requestCategory, Long categoryId) throws ResponseStatusException {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setCategoryName(requestCategory.getCategoryName());
            categoryRepository.save(category);
            return category;
        } else {
            throw new ResponseStatusException(NOT_FOUND, String.format("Category don't find at this %s id", categoryId));
        }
    }


}
