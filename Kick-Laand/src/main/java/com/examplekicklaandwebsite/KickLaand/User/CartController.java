package com.examplekicklaandwebsite.KickLaand.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CartController {

    private final CartRepository cartRepository;
    private final UserAccountRepository userAccountRepository;  // Assuming you have a UserRepository

    public CartController(CartRepository cartRepository, UserAccountRepository userAccountRepository) {
        this.cartRepository = cartRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @GetMapping("/Backend/Cart")
    @Transactional
    public ResponseEntity<?> getUserCartItems(@RequestParam String email) {
        try {
            UserAccount user = userAccountRepository.findByEmail(email);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            } else {
                List<Cart> userCart = cartRepository.findByUser(user);

                if (userCart.isEmpty()) {
                    return ResponseEntity.ok("User has no cart items");
                } else {
                    List<Map<String, Object>> filteredCartList = userCart.stream()
                            .map(cart -> {
                                Map<String, Object> filteredCartItem = new HashMap<>();
                                filteredCartItem.put("id", cart.getId());
                                filteredCartItem.put("name", cart.getName());
                                filteredCartItem.put("price", cart.getPrice());
                                filteredCartItem.put("category", cart.getCategory());
                                filteredCartItem.put("quantity", cart.getQuantity());
                                filteredCartItem.put("image1", cart.getImage1());
                                filteredCartItem.put("image2", cart.getImage2());
                                filteredCartItem.put("image3", cart.getImage3());
                                filteredCartItem.put("image4", cart.getImage4());
                                // Exclude user information
                                // filteredCartItem.put("userId", cart.getUser().getId()); // Optionally, you can include user ID if needed
                                return filteredCartItem;
                            })
                            .collect(Collectors.toList());

                    return ResponseEntity.ok(filteredCartList);
                }
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    
    @PostMapping("/Backend/Cart")
    public ResponseEntity<?> addToCartWithUser(@Valid @RequestBody CartWithUserRequest request) {
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
            cartItem.setImage1(request.getImage1());
            cartItem.setImage2(request.getImage2());
            cartItem.setImage3(request.getImage3());
            cartItem.setImage4(request.getImage4());
            cartItem.setUser(user);

            // Save the Cart object
            cartRepository.save(cartItem);

            List<Map<String, Object>> filteredCartList = cartRepository.findByUser(user).stream()
                    .map(cart -> {
                        Map<String, Object> filteredCartItem = new HashMap<>();
                        filteredCartItem.put("id", cart.getId());
                        filteredCartItem.put("name", cart.getName());
                        filteredCartItem.put("price", cart.getPrice());
                        filteredCartItem.put("category", cart.getCategory());
                        filteredCartItem.put("quantity", cart.getQuantity());
                        filteredCartItem.put("image1", cart.getImage1());
                        filteredCartItem.put("image2", cart.getImage2());
                        filteredCartItem.put("image3", cart.getImage3());
                        filteredCartItem.put("image4", cart.getImage4());
                        // Exclude user information
                        // filteredCartItem.put("userId", cart.getUser().getId()); // Optionally, you can include user ID if needed
                        return filteredCartItem;
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok(filteredCartList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    
//    @DeleteMapping("/Backend/Cart")
//    public ResponseEntity<?> deleteCartItem(@RequestParam Integer userId, @RequestParam Long productId) {
//        try {
//            UserAccount user = userAccountRepository.findById(userId)
//                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
//
//            Cart cartItem = cartRepository.findByProductidandUser(productId);
//            if (cartItem != null) {
//                cartRepository.delete(cartItem);
//                return ResponseEntity.ok("OK");
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
//        }
//    }

    
    
    
}
