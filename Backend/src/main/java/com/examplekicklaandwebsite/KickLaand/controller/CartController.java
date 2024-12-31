package com.examplekicklaandwebsite.KickLaand.controller;

import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.request.CartRequest;
import com.examplekicklaandwebsite.KickLaand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examplekicklaandwebsite.KickLaand.dto.UserCartDTO;
import com.examplekicklaandwebsite.KickLaand.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    @Autowired
    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<?> getUserCartItems(@RequestHeader("Authorization") String jwt) throws Exception {
        UserAccount user = userService.findUserByJwtToken(jwt);
        return cartService.getUserCartItems(user);
    }

    @PostMapping()
    public ResponseEntity<?> addToCart(@RequestHeader("Authorization") String jwt, @RequestBody UserCartDTO req)
            throws Exception {
        UserAccount user = userService.findUserByJwtToken(jwt);
        return cartService.addToCart(req, user);
    }

    @PutMapping()
    public ResponseEntity<?> updateCart(@RequestHeader("Authorization") String jwt, @RequestBody CartRequest req) throws Exception {
        UserAccount user = userService.findUserByJwtToken(jwt);
        return cartService.updateCart(user, req);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteCartItem(@RequestHeader("Authorization") String jwt, @RequestBody CartRequest req) throws Exception {
        UserAccount user = userService.findUserByJwtToken(jwt);
        return cartService.deleteCartItem(user, req);
    }
}
