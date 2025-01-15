package com.ecommerce.project.service.impl;

import com.ecommerce.project.exceptions.ApiException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDto;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.repositories.CategoryRepository;
import com.ecommerce.project.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);
        List<Category> categories = categoryPage.getContent();
        if (categories.isEmpty()) {
            throw new ApiException("Not category created till now.");
        }
        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .toList();

        CategoryResponse categoryResponse =  new CategoryResponse();
        categoryResponse.setContent(categoryDtos);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());
        return categoryResponse;
    }
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        String categoryName = categoryDto.getCategoryName();
        Category categoryFromDbAtName = categoryRepository.findCategoryByCategoryName(categoryName);
        if (categoryFromDbAtName != null) {
            throw new ApiException("Category with the name " + categoryName + " already exists!");
        }
         Category savedCategory =  categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
        public CategoryDto deleteCategory(Long categoryId) throws ResponseStatusException {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() ->  new ResourceNotFoundException("Category","categoryId",categoryId));
            categoryRepository.deleteById(categoryId);
            return modelMapper.map(category, CategoryDto.class);
        }

    @Override
    public CategoryDto updateCategory(CategoryDto requestCategory, Long categoryId) throws ResponseStatusException {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setCategoryName(requestCategory.getCategoryName());
            Category updateCategory = categoryRepository.save(category);

            return modelMapper.map(updateCategory, CategoryDto.class);
        } else {
            throw new ResourceNotFoundException("Category","categoryId",categoryId);
        }
    }


}
