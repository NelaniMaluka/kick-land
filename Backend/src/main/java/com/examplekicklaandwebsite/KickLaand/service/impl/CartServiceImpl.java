package com.examplekicklaandwebsite.KickLaand.service.impl;

import com.examplekicklaandwebsite.KickLaand.request.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.examplekicklaandwebsite.KickLaand.dto.UserCartDTO;
import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.model.UserCarts;
import com.examplekicklaandwebsite.KickLaand.repository.CartRepository;
import com.examplekicklaandwebsite.KickLaand.service.CartService;
import com.examplekicklaandwebsite.KickLaand.util.FilterLists;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public ResponseEntity<?> getUserCartItems(UserAccount user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        List<UserCarts> userCart = user.getUserCart();
        if (userCart.isEmpty()) {
            return ResponseEntity.ok("Cart is empty");
        }
        return ResponseEntity.ok(FilterLists.getFilteredCartList(userCart));
    }


    @Override
    public ResponseEntity<?> addToCart(UserCartDTO req, UserAccount user) {
        try {
            UserCarts cartItem = new UserCarts();
            cartItem.setProductId(req.getProductId());
            cartItem.setQuantity(req.getQuantity());
            cartItem.setProductSize(req.getSize());
            cartItem.setUserId(user);
            cartItem.setPrice(req.getPrice());

            cartRepository.save(cartItem);

            Object filteredCartList = FilterLists.getFilteredCartList(user.getUserCart());
            return ResponseEntity.ok(filteredCartList);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data integrity violation");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @Override
    public ResponseEntity<?> updateCart(UserAccount user, CartRequest req) {
        try {
            List<UserCarts> userCartItems = user.getUserCart();

            if (userCartItems.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                UserCarts cartItemToUpdate = userCartItems.stream()
                        .filter(cart -> cart.getProductId().equals(req.productId()))
                        .findFirst()
                        .orElseThrow(() -> new EntityNotFoundException("Cart item not found"));

                cartItemToUpdate.setQuantity(req.quantity());

                cartRepository.save(cartItemToUpdate);

                Object filteredCartList = FilterLists.getFilteredCartList(user.getUserCart());
                return ResponseEntity.ok(filteredCartList);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An unexpected error occurred");
        }
    }

    @Override
    public ResponseEntity<?> deleteCartItem(UserAccount user, CartRequest req) {
        try {
            cartRepository.deleteByProductIdAndUserId(req.productId(), user);

            List<UserCarts> userCartItems = user.getUserCart();
            Object filteredCartList = FilterLists.getFilteredCartList(userCartItems);
            return ResponseEntity.ok(filteredCartList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An unexpected error occurred");
        }
    }

}
