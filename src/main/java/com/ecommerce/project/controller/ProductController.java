package com.ecommerce.project.controller;

import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.ProductDto;
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
    public ResponseEntity<ProductDto> addProduct(@RequestBody Product product,
                                                 @PathVariable Long categoryId) {
        ProductDto responseProductDto = productService.addProduct(categoryId, product);
        return ResponseEntity.ok(responseProductDto);
    }
}
