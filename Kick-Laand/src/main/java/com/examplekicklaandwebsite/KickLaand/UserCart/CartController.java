package com.examplekicklaandwebsite.KickLaand.UserCart;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examplekicklaandwebsite.KickLaand.User.UserAccount;
import com.examplekicklaandwebsite.KickLaand.User.UserAccountRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CartController {

    private final CartRepository cartRepository;
    private final UserAccountRepository userAccountRepository; // Assuming you have a UserRepository

    public CartController(CartRepository cartRepository, UserAccountRepository userAccountRepository) {
        this.cartRepository = cartRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @GetMapping("/api/user/cart")
    @Transactional
    public ResponseEntity<?> getUserCartItems(@RequestParam String email) {
        try {
            UserAccount user = userAccountRepository.findByEmail(email);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            } else {
                List<Cart> userCart = user.getCart();

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

    @PostMapping("/api/user/cart")
    public ResponseEntity<?> addToCart(@Valid @RequestBody CartWithUserRequest request) {
        try {
            // Retrieve the user or handle user creation logic
            UserAccount user = userAccountRepository.findById(request.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + request.getUserId()));

            // Create a Cart object
            Cart cartItem = new Cart();
            // Set properties for the Cart object based on your provided data
            cartItem.setId(request.getId());
            cartItem.setName(request.getName());
            cartItem.setPrice(request.getPrice());
            cartItem.setCategory(request.getCategory());
            cartItem.setQuantity(request.getQuantity());
            cartItem.setSize(request.getSize());
            cartItem.setImage1(request.getImage1());
            cartItem.setImage2(request.getImage2());
            cartItem.setImage3(request.getImage3());
            cartItem.setImage4(request.getImage4());
            cartItem.setUser(user);

            // Save the Cart object
            cartRepository.save(cartItem);

            Object filteredCartList = getFilteredCartList(user.getCart());
            return ResponseEntity.ok(filteredCartList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("/api/user/cart")
    public ResponseEntity<?> updateCart(
            @RequestParam("userId") Integer userId,
            @RequestParam("productId") Integer productId,
            @RequestParam("productQuantity") Integer productQuantity) {

        UserAccount user = userAccountRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        List<Cart> userCartItems = user.getCart();

        if (userCartItems.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            // Find the cart item with the specified productId that belongs to the user
            Optional<Cart> cartItemToUpdate = userCartItems.stream()
                    .filter(cart -> cart.getId().equals(productId))
                    .findFirst();

            if (cartItemToUpdate.isPresent()) {
                // Update the quantity of the existing cart item
                Cart existingCartItem = cartItemToUpdate.get();
                existingCartItem.setQuantity(productQuantity);

                // Save the updated cart item
                cartRepository.save(existingCartItem);

                // Return the updated cart list
                Object filteredCartList = getFilteredCartList(user.getCart());
                return ResponseEntity.ok(filteredCartList);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @Transactional
    @DeleteMapping("/api/user/cart")
    public ResponseEntity<?> deleteCartItem(@RequestParam @NonNull Integer userId,
            @RequestParam @NonNull Integer productId) {
        try {
            UserAccount user = userAccountRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

            List<Cart> userCartItems = user.getCart();
            if (userCartItems.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                // Find the cart item with the specified productId that belongs to the user
                Optional<Cart> cartItemToDelete = userCartItems.stream()
                        .filter(cart -> cart.getId().equals(productId))
                        .findFirst();

                if (cartItemToDelete.isPresent()) {
                    // Remove the cart item from the user's cart
                    userCartItems.remove(cartItemToDelete.get());
                    cartRepository.deleteById(productId);

                    // Return the updated cart list
                    Object filteredCartList = getFilteredCartList(user.getCart());
                    return ResponseEntity.ok(filteredCartList);
                } else {
                    return ResponseEntity.notFound().build();
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }

    private List<Map<String, Object>> getFilteredCartList(List<Cart> userCart) {
        return userCart.stream()
                .map(cart -> {
                    Map<String, Object> filteredCartItem = new HashMap<>();
                    filteredCartItem.put("id", cart.getId());
                    filteredCartItem.put("name", cart.getName());
                    filteredCartItem.put("price", cart.getPrice());
                    filteredCartItem.put("category", cart.getCategory());
                    filteredCartItem.put("quantity", cart.getQuantity());
                    filteredCartItem.put("size", cart.getSize());
                    filteredCartItem.put("image1", cart.getImage1());
                    filteredCartItem.put("image2", cart.getImage2());
                    filteredCartItem.put("image3", cart.getImage3());
                    filteredCartItem.put("image4", cart.getImage4());
                    return filteredCartItem;
                })
                .collect(Collectors.toList());
    }

}
