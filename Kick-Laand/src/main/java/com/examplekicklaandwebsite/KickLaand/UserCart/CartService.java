package com.examplekicklaandwebsite.KickLaand.UserCart;

import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<?> getUserCartItems(String email);
    ResponseEntity<?> addToCart(UserCartDTO request);
    ResponseEntity<?> updateCart(Integer userId, Integer productId, Integer productQuantity);
    ResponseEntity<?> deleteCartItem(Integer userId, Integer productId);
}

