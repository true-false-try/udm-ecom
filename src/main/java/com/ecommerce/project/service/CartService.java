package com.ecommerce.project.service;

import com.ecommerce.project.payload.CartDto;

public interface CartService {
    CartDto addProductToCart(Long productId, Integer quantity);
}
