package com.ecommerce.project.controller;


import com.ecommerce.project.payload.ProductDto;
import com.ecommerce.project.payload.ProductResponse;
import com.ecommerce.project.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.ecommerce.project.config.AppConstants.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody ProductDto productDto,
                                                 @PathVariable Long categoryId) {
        ProductDto responseProductDto = productService.addProduct(categoryId, productDto);
        return ResponseEntity.ok(responseProductDto);
    }

    @GetMapping("public/products")
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = PAGE_NUMBER_NAME, defaultValue = PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = PAGE_NUMBER_SIZE, defaultValue = PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = PAGE_SORT_BY, defaultValue =  SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = PAGE_SORT_ORDER, defaultValue = SORT_DIR, required = false) String sortOrder
    ) {
        ProductResponse productResponse = productService.getAllProducts(pageNumber, pageSize, sortBy, sortOrder, keyword, category);
        return ResponseEntity.ok(productResponse);
    }
    @GetMapping("public/categories/{categoryId}/product")
    public ResponseEntity<ProductResponse> getProductsByCategory(
            @RequestParam(name = PAGE_NUMBER_NAME, defaultValue = PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = PAGE_NUMBER_SIZE, defaultValue = PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = PAGE_SORT_BY, defaultValue =  SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = PAGE_SORT_ORDER, defaultValue = SORT_DIR, required = false) String sortOrder,
            @PathVariable Long categoryId) {
        ProductResponse productResponse = productService.searchByCategory(pageNumber, pageSize, sortBy, sortOrder, categoryId);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductsByKeyword(
            @RequestParam(name = PAGE_NUMBER_NAME, defaultValue = PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = PAGE_NUMBER_SIZE, defaultValue = PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = PAGE_SORT_BY, defaultValue =  SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = PAGE_SORT_ORDER, defaultValue = SORT_DIR, required = false) String sortOrder,
            @PathVariable String keyword) {
        ProductResponse productResponse = productService.searchProductsByKeyword(pageNumber, pageSize, sortBy, sortOrder,keyword);
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping("admin/products/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto,
                                                    @PathVariable Long productId) {
        ProductDto responseProduct = productService.updateProduct(productDto, productId);
        return ResponseEntity.ok(responseProduct);
    }

    @DeleteMapping("admin/categories/{productId}")
    public ResponseEntity<ProductDto> deleteCategory(@PathVariable Long productId) {
        ProductDto responseProduct = productService.deleteProduct(productId);
        return ResponseEntity.ok(responseProduct);

    }

    @PutMapping("public/products/{productId}/image")
    public ResponseEntity<ProductDto> updateProductImage(@PathVariable Long productId,
                                                         @RequestParam("image") MultipartFile image) throws IOException {
        ProductDto responseProductDto = productService.updateProductImage(productId, image);
        return ResponseEntity.ok(responseProductDto);
    }
}
