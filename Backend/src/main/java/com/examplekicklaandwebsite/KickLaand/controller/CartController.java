package com.examplekicklaandwebsite.KickLaand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examplekicklaandwebsite.KickLaand.dto.UserCartDTO;
import com.examplekicklaandwebsite.KickLaand.service.CartService;

@RestController
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/api/user/cart")
    public ResponseEntity<?> getUserCartItems(@RequestParam String email) {
        return cartService.getUserCartItems(email);
    }

    @PostMapping("/api/user/cart")
    public ResponseEntity<?> addToCart(@RequestBody UserCartDTO request) {
        return cartService.addToCart(request);
    }

    @PutMapping("/api/user/cart")
    public ResponseEntity<?> updateCart(
            @RequestParam("userId") Integer userId,
            @RequestParam("productId") Integer productId,
            @RequestParam("productQuantity") Integer productQuantity) {
        return cartService.updateCart(userId, productId, productQuantity);
    }

    @DeleteMapping("/api/user/cart")
    public ResponseEntity<?> deleteCartItem(
            @RequestParam("userId") Integer userId,
            @RequestParam("productId") Integer productId) {
        return cartService.deleteCartItem(userId, productId);
    }
}
