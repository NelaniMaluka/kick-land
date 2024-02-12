package com.examplekicklaandwebsite.KickLaand.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
public class CartController {

    private final CartRepository cartRepository;
    private final UserAccountRepository userAccountRepository;  // Assuming you have a UserRepository

    public CartController(CartRepository cartRepository, UserAccountRepository userAccountRepository) {
        this.cartRepository = cartRepository;
        this.userAccountRepository = userAccountRepository;
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
            cartItem.setImage1(request.getImage1());
            cartItem.setImage2(request.getImage2());
            cartItem.setImage3(request.getImage3());
            cartItem.setImage4(request.getImage4());
            cartItem.setUser(user);

            // Save the Cart object
            cartRepository.save(cartItem);

            List<Map<String, Object>> filteredCartList = cartRepository.findAll().stream()
                    .map(cart -> {
                        Map<String, Object> filteredCartItem = new HashMap<>();
                        filteredCartItem.put("id", cart.getId());
                        filteredCartItem.put("name", cart.getName());
                        filteredCartItem.put("price", cart.getPrice());
                        filteredCartItem.put("category", cart.getCategory());
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
}
