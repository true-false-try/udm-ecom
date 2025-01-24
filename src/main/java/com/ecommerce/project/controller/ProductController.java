package com.ecommerce.project.controller;

import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.ProductDto;
import com.ecommerce.project.payload.ProductResponse;
import com.ecommerce.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto,
                                                 @PathVariable Long categoryId) {
        ProductDto responseProductDto = productService.addProduct(categoryId, productDto);
        return ResponseEntity.ok(responseProductDto);
    }
    @GetMapping("public/products")
    public ResponseEntity<ProductResponse> getAllProducts() {
        ProductResponse productResponse = productService.getAllProducts();
        return ResponseEntity.ok(productResponse);
    }
    @GetMapping("public/categories/{categoryId}/product")
    public ResponseEntity<ProductResponse> getProductsByCategory(@PathVariable Long categoryId) {
        ProductResponse productResponse = productService.searchByCategory(categoryId);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductsByKeyword(@PathVariable String keyword) {
        ProductResponse productResponse = productService.searchProductsByKeyword(keyword);
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping("admin/products/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,
                                                    @PathVariable Long productId) {
        ProductDto responseProduct = productService.updateProduct(productDto, productId);
        return ResponseEntity.ok(responseProduct);
    }

    @DeleteMapping("admin/categories/{productId}")
    public ResponseEntity<ProductDto> deleteCategory(@PathVariable Long productId) {
        ProductDto responseProduct = productService.deleteProduct(productId);
        return ResponseEntity.ok(responseProduct);

    }
}
