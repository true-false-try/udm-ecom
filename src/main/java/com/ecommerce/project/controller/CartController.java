package com.ecommerce.project.controller;

import com.ecommerce.project.model.Cart;
import com.ecommerce.project.payload.CartDto;
import com.ecommerce.project.repositories.CartRepository;
import com.ecommerce.project.service.CartService;
import com.ecommerce.project.util.AuthUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class CartController {
    private final CartRepository cartRepository;
    private final AuthUtil authUtil;
    private final CartService cartService;

    @PostMapping("carts/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartDto> addProductToCart(@PathVariable Long productId,
                                                    @PathVariable Integer quantity) {
        CartDto cartDto = cartService.addProductToCart(productId, quantity);
        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);
    }

    @GetMapping("carts")
    public ResponseEntity<List<CartDto>> getCarts() {
        List<CartDto> cartDtos = cartService.getAllCarts();
        return new ResponseEntity<>(cartDtos, HttpStatus.NOT_FOUND);
    }
    @GetMapping("carts/users/cart")
    public ResponseEntity<CartDto> getCartById() {
        String emailId = authUtil.loggedInEmail();
        Cart cart = cartRepository.findCartByEmail(emailId);
        Long cartId = cart.getCartId();
        CartDto cartDto = cartService.getCart(emailId, cartId);
        return ResponseEntity.ok().body(cartDto);
    }

}
