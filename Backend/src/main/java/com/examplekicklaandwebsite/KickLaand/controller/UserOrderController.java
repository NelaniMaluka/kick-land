package com.examplekicklaandwebsite.KickLaand.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.model.UserOrders;
import com.examplekicklaandwebsite.KickLaand.repository.UserAccountRepository;
import com.examplekicklaandwebsite.KickLaand.repository.UserOrderRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

//@RestController
@Validated
public class UserOrderController {

    private final UserAccountRepository userAccountRepository;

    private UserOrderRepository userOrderRepository;
    
    public UserOrderController(UserOrderRepository userOrderRepository, UserAccountRepository userAccountRepository) {
        super();
        this.userOrderRepository = userOrderRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @GetMapping("/api/user/orders")
    @Transactional
    public ResponseEntity<?> getUserOrderItems(@RequestParam String email) {
        try {
        	UserAccount user = userAccountRepository.findByEmail(email);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            } else {
                // List<UserOrders> userOrders = user.getOrders();

                // if (userOrders.isEmpty()) {
                // return ResponseEntity.ok(null);
                // } else {
                // return ResponseEntity.ok(userOrders);
                // }
                return ResponseEntity.ok(null);
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/api/user/orders")
    public ResponseEntity<?> addToCart(@Valid UserOrders userOrder) {
        try {

            // Save the Cart object
            // userOrdersRepository.save(userOrders);

            // Object filteredCartList = getFilteredCartList(user.getCart());
            return ResponseEntity.ok(userOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

}
