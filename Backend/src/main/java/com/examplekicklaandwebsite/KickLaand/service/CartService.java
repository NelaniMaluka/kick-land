package com.examplekicklaandwebsite.KickLaand.service;

import org.springframework.http.ResponseEntity;

import com.examplekicklaandwebsite.KickLaand.dto.UserCartDTO;

public interface CartService {
    ResponseEntity<?> getUserCartItems(String email);
    ResponseEntity<?> addToCart(UserCartDTO request);
    ResponseEntity<?> updateCart(Integer userId, Integer productId, Integer productQuantity);
    ResponseEntity<?> deleteCartItem(Integer userId, Integer productId);
}

