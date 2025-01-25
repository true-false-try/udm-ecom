package com.ecommerce.project.service;

import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.ProductDto;
import com.ecommerce.project.payload.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
     ProductDto addProduct(Long categoryId, ProductDto productDto);
     ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
     ProductResponse searchByCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, Long categoryId);
     ProductResponse searchProductsByKeyword(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, String keyword);
     ProductDto updateProduct(ProductDto productDto, Long productId);
     ProductDto deleteProduct(Long productId);
     ProductDto updateProductImage(Long productId, MultipartFile image) throws IOException;
}
