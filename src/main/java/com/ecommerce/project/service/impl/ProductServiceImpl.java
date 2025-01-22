package com.ecommerce.project.service.impl;

import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.ProductDto;
import com.ecommerce.project.repositories.CategoryRepository;
import com.ecommerce.project.repositories.ProductRepository;
import com.ecommerce.project.service.ProductService;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository; 
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    @Override
    public ProductDto addProduct(Long categoryId, Product product) {
         Category category = categoryRepository.findById(categoryId)
                 . orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
         product.setImage("default.png");
         product.setCategory(category);
         double specialPrice = product.getPrice() - (product.getDiscount() * 0.01) *  product.getPrice();
         product.setSpecialPrice(specialPrice);
         Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }
}
