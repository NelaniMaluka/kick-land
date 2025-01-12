package com.examplekicklaandwebsite.KickLaand.service.impl;

import com.examplekicklaandwebsite.KickLaand.request.CartRequest;
import com.examplekicklaandwebsite.KickLaand.response.ErrorResponse;
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

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public ResponseEntity<?> getUserCartItems(UserAccount user) {
        try {
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("User Not Found", "No user exists with the provided email address."));
            }
            List<UserCarts> userCart = user.getUserCart();
            if (userCart.isEmpty()) {
                return ResponseEntity.ok("User doesn't have anything in their cart");

            }
            return ResponseEntity.ok(FilterLists.getFilteredCartList(userCart));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal Server Error", "An error occurred while retrieving your cart. Please try again later."));
        }
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
            return new ResponseEntity<>(filteredCartList, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Data integrity violation","One or more fields contain invalid data or violate data constraints. Please check the input values."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal Server Error", "An error occurred while adding to your cart. Please try again later."));
        }
    }

    @Override
    public ResponseEntity<?> updateCart(UserAccount user, CartRequest req) {
        try {
            List<UserCarts> userCartItems = user.getUserCart();

            // Cart is empty check
            if (userCartItems.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("Cart is empty", "No items found in the user's cart."));
            }

            // Updating the cart item
            UserCarts cartItemToUpdate = userCartItems.stream()
                    .filter(cart -> cart.getProductId().equals(req.productId()))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException("Cart item not found"));

            // Updating cart quantity
            cartItemToUpdate.setQuantity(req.quantity());
            cartRepository.save(cartItemToUpdate);

            // Returning filtered cart list
            Object filteredCartList = FilterLists.getFilteredCartList(user.getUserCart());
            return ResponseEntity.ok(filteredCartList);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Item not found", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal Server Error", "An error occurred while updating your cart. Please try again later."));
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
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Internal Server Error", "An error occurred while deleting your cart item. Please try again later."));
        }
    }

}
