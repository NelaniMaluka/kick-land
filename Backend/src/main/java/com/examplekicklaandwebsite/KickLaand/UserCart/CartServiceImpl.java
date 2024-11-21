package com.examplekicklaandwebsite.KickLaand.UserCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.examplekicklaandwebsite.KickLaand.User.UserAccount;
import com.examplekicklaandwebsite.KickLaand.User.UserAccountRepository;

import javax.persistence.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> getUserCartItems(String email) {
        try {
            UserAccount user = userAccountRepository.findByEmail(email);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            } else {
                List<UserCarts> userCart = user.getUserCart();

                if (userCart.isEmpty()) {
                    return ResponseEntity.ok(null);
                } else {
                    Object filteredCartList = getFilteredCartList(userCart);
                    return ResponseEntity.ok(filteredCartList);
                }
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> addToCart(UserCartDTO request) {
        try {
            Integer userId = request.getUserId();
            UserAccount user = userAccountRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

            UserCarts cartItem = new UserCarts();
            cartItem.setProductId(request.getProductId());
            cartItem.setQuantity(request.getQuantity());
            cartItem.setProductSize(request.getSize());
            cartItem.setUserId(user);

            cartRepository.save(cartItem);

            Object filteredCartList = getFilteredCartList(user.getUserCart());
            return ResponseEntity.ok(filteredCartList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateCart(Integer userId, Integer productId, Integer productQuantity) {
        try {
            UserAccount user = userAccountRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

            List<UserCarts> userCartItems = user.getUserCart();

            if (userCartItems.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                UserCarts cartItemToUpdate = userCartItems.stream()
                        .filter(cart -> cart.getProductId().equals(productId))
                        .findFirst()
                        .orElseThrow(() -> new EntityNotFoundException("Cart item not found"));

                cartItemToUpdate.setQuantity(productQuantity);

                cartRepository.save(cartItemToUpdate);

                Object filteredCartList = getFilteredCartList(user.getUserCart());
                return ResponseEntity.ok(filteredCartList);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteCartItem(Integer userId, Integer productId) {
        try {
            UserAccount user = userAccountRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

            List<UserCarts> userCartItems = user.getUserCart();

            if (userCartItems.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                userCartItems.removeIf(cart -> cart.getProductId().equals(productId));
                cartRepository.deleteByProductIdAndUserId(productId, userId);

                Object filteredCartList = getFilteredCartList(user.getUserCart());
                return ResponseEntity.ok(filteredCartList);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private List<Map<String, Object>> getFilteredCartList(List<UserCarts> userCart) {
        return userCart.stream()
                .map(cart -> {
                    Map<String, Object> filteredCartItem = new HashMap<>();
                    filteredCartItem.put("id", cart.getUserCartId());
                    filteredCartItem.put("productId", cart.getProductId());
                    filteredCartItem.put("quantity", cart.getQuantity());
                    filteredCartItem.put("size", cart.getProductSize());
                    return filteredCartItem;
                })
                .collect(Collectors.toList());
    }
}
