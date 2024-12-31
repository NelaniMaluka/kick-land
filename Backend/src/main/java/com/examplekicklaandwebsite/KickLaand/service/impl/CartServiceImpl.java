package com.examplekicklaandwebsite.KickLaand.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.examplekicklaandwebsite.KickLaand.dto.UserCartDTO;
import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.model.UserCarts;
import com.examplekicklaandwebsite.KickLaand.repository.CartRepository;
import com.examplekicklaandwebsite.KickLaand.repository.UserAccountRepository;
import com.examplekicklaandwebsite.KickLaand.service.CartService;
import com.examplekicklaandwebsite.KickLaand.util.FilterLists;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserAccountRepository userAccountRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserAccountRepository userAccountRepository) {
        this.cartRepository = cartRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public ResponseEntity<?> getUserCartItems(UserAccount user) {
        try {
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            } else {
                List<UserCarts> userCart = user.getUserCart();
                if (userCart.isEmpty()) {
                    return ResponseEntity.ok("Cart is empty");
                } else {
                    Object filteredCartList = FilterLists.getFilteredCartList(userCart);
                    return ResponseEntity.ok(filteredCartList);
                }
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data integrity violation");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> addToCart(UserCartDTO request, UserAccount user) {
        if (request.getProductId() == null || request.getQuantity() <= 0) {
            return ResponseEntity.badRequest().body("Invalid product or quantity");
        }

        try {
            UserCarts cartItem = UserCarts.builder()
                    .productId(request.getProductId())
                    .quantity(request.getQuantity())
                    .productSize(request.getSize())
                    .userId(user)
                    .price(request.getPrice())
                    .build();

            cartRepository.save(cartItem);

            Object filteredCartList = FilterLists.getFilteredCartList(user.getUserCart());
            return ResponseEntity.ok(filteredCartList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateCart(UserAccount user, Integer productId, Integer productQuantity) {
        try {
            List<UserCarts> userCartItems = user.getUserCart();

            if (userCartItems.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                UserCarts cartItemToUpdate = userCartItems.stream()
                        .filter(cart -> cart.getProductId().equals(productId))
                        .findFirst()
                        .orElseThrow(() -> new EntityNotFoundException("Cart item not found"));

                cartItemToUpdate = cartItemToUpdate.toBuilder()
                        .quantity(productQuantity)
                        .build();

                cartRepository.save(cartItemToUpdate);

                Object filteredCartList = FilterLists.getFilteredCartList(user.getUserCart());
                return ResponseEntity.ok(filteredCartList);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteCartItem(UserAccount user, Integer productId) {
        try {
            cartRepository.deleteByProductIdAndUserId(productId, user);

            List<UserCarts> userCartItems = user.getUserCart();
            Object filteredCartList = FilterLists.getFilteredCartList(userCartItems);
            return ResponseEntity.ok(filteredCartList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
