package com.ecommerce.project.service.impl;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import com.ecommerce.project.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

    import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private Long nextId = 1L;

    @Override
    public List<Category> getAllCategories() {
        return
                categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categoryRepository.save(category);
    }

    @Override
        public String deleteCategory(Long categoryId) throws ResponseStatusException {
            List<Category> categories = categoryRepository.findAll();
            Category category = categories.stream()
                    .filter(c -> c.getCategoryId().equals(categoryId))
                    .findFirst().orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Resource not found."));
            categoryRepository.delete(category);
            return category.toString();
        }

    @Override
    public Category updateCategory(Category requestCategory, Long categoryId) throws ResponseStatusException {
        List<Category> categories = categoryRepository.findAll();
        Optional<Category> optionalCategory = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();
        if (optionalCategory.isPresent()) {
            Category existCategory = optionalCategory.get();
            existCategory.setCategoryName(requestCategory.getCategoryName());
            Category savedCategory = categoryRepository.save(existCategory);
            return savedCategory ;
        } else {
            throw new ResponseStatusException(NOT_FOUND, String.format("Category don't find at this %s id", categoryId));
        }
    }


}
