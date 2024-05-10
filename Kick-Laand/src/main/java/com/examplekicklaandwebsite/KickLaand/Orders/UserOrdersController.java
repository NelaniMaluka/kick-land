package com.examplekicklaandwebsite.KickLaand.Orders;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examplekicklaandwebsite.KickLaand.User.UserAccount;
import com.examplekicklaandwebsite.KickLaand.User.UserAccountRepository;
import com.examplekicklaandwebsite.KickLaand.UserCart.Cart;
import com.examplekicklaandwebsite.KickLaand.UserCart.CartRepository;

//@RestController
@Validated
public class UserOrdersController {

	private UserOrdersRepository userOrdersRepository;
	private final UserAccountRepository userAccountRepository; 

	public UserOrdersController(UserOrdersRepository userOrdersRepository, UserAccountRepository userAccountRepository) {
		super();
		this.userOrdersRepository = userOrdersRepository;
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
                //List<UserOrders> userOrders = user.getOrders();

                //if (userOrders.isEmpty()) {
                //    return ResponseEntity.ok(null);
                //} else {
                //    return ResponseEntity.ok(userOrders);
                //}
                return ResponseEntity.ok(null);
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
	
    @PostMapping("/api/user/orders")
    public ResponseEntity<?> addToCart(@Valid UserOrders userOrders) {
        try {

            // Save the Cart object
            //userOrdersRepository.save(userOrders);

            //Object filteredCartList = getFilteredCartList(user.getCart());
            return ResponseEntity.ok(userOrders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
	
}
