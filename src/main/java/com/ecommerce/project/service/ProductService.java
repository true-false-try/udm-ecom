package com.ecommerce.project.service;

import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.ProductDto;
import com.ecommerce.project.payload.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
     ProductDto addProduct(Long categoryId, ProductDto productDto);
     ProductResponse getAllProducts();
     ProductResponse searchByCategory(Long  categoryId);
     ProductResponse searchProductsByKeyword(String keyword);
     ProductDto updateProduct(ProductDto productDto, Long productId);
     ProductDto deleteProduct(Long productId);
     ProductDto updateProductImage(Long productId, MultipartFile image) throws IOException;
}
