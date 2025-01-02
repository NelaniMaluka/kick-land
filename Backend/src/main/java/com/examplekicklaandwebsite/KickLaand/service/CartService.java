package com.examplekicklaandwebsite.KickLaand.service;

import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.request.CartRequest;
import org.springframework.http.ResponseEntity;

import com.examplekicklaandwebsite.KickLaand.dto.UserCartDTO;

public interface CartService {
    ResponseEntity<?> getUserCartItems(UserAccount user);
    ResponseEntity<?> addToCart(UserCartDTO request, UserAccount user);
    ResponseEntity<?> updateCart(UserAccount user, CartRequest req);
    ResponseEntity<?> deleteCartItem(UserAccount user, Integer productId);
}

