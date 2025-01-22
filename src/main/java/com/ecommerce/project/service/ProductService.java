package com.ecommerce.project.service;

import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.ProductDto;

public interface ProductService {
     ProductDto addProduct(Long categoryId, Product product);
}
