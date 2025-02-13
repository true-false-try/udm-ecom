package com.ecommerce.project.service;

import com.ecommerce.project.payload.CartDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CartService {
    CartDto addProductToCart(Long productId, Integer quantity);
    List<CartDto> getAllCarts();
    CartDto getCart(String emailId, Long cartId);

    @Transactional
    CartDto updateProductQuantityInCart(Long productId, Integer quantity);
    @Transactional
    String deleteProductFromCart(Long cartId, Long productId);

    void updateProductInCarts(Long cartId, Long productId);
}
