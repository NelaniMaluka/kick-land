package com.examplekicklaandwebsite.KickLaand.service.impl;

import com.examplekicklaandwebsite.KickLaand.request.OrderRequest;
import com.examplekicklaandwebsite.KickLaand.model.CompletedOrders;
import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.model.UserCarts;
import com.examplekicklaandwebsite.KickLaand.model.UserOrders;
import com.examplekicklaandwebsite.KickLaand.repository.CartRepository;
import com.examplekicklaandwebsite.KickLaand.repository.UserAccountRepository;
import com.examplekicklaandwebsite.KickLaand.response.ErrorResponse;
import com.examplekicklaandwebsite.KickLaand.response.PaymentResponse;
import com.examplekicklaandwebsite.KickLaand.service.OrderService;
import com.examplekicklaandwebsite.KickLaand.service.PaymentService;
import com.examplekicklaandwebsite.KickLaand.util.FilterLists;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserAccountRepository userAccountRepository;
    private final CartRepository cartRepository;
    private final PaymentService paymentService;

    public OrderServiceImpl(UserAccountRepository userAccountRepository, CartRepository cartRepository,
            PaymentService paymentService) {
        this.userAccountRepository = userAccountRepository;
        this.cartRepository = cartRepository;
        this.paymentService = paymentService;
    }

    @Override
    public ResponseEntity<?> getOrder(UserAccount user) {
        try {
            // Retrieve user's orders
            List<UserOrders> userOrders = user.getOrders();

            // If no orders exist, respond with 200 OK and null (or an empty response as needed)
            if (userOrders.isEmpty()) {
                return ResponseEntity.ok(new ErrorResponse("No Orders Found", "The user has no orders associated with their account."));
            } else {
                Object filteredOrderList = FilterLists.getFilteredOrderList(userOrders);
                return ResponseEntity.ok(filteredOrderList);
            }

        } catch (EntityNotFoundException e) {
            // This could indicate that the user wasn't found, or no orders could be retrieved.
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("User Not Found", "No user found with the provided information. Please check the user details and try again."));
        } catch (DataIntegrityViolationException e) {
            // Data integrity issues occurred while retrieving orders (conflicting data).
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Data Integrity Violation", "There was an issue with retrieving the orders due to a data conflict. Please try again."));
        } catch (Exception e) {
            // A generic fallback error message for unexpected exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal Server Error", "An unexpected error occurred while fetching your orders. Please try again later."));
        }
    }


    public ResponseEntity<?> createOrder(OrderRequest req, UserAccount user) throws Exception {
        try {

            List<UserCarts> userCartItems = user.getUserCart();

            if (userCartItems.isEmpty()) {
                return ResponseEntity.ok(null);
            } else {

                // Creating the payment link via the service
                PaymentResponse response = paymentService.createPaymentLink(userCartItems);

                LocalDateTime dateTime = LocalDateTime.now();

                // Create the UserOrders entity
                UserOrders userOrders = new UserOrders();
                userOrders.setUserId(user);
                userOrders.setFirstname(req.firstname());
                userOrders.setLastname(req.lastname());
                userOrders.setZIPCode(req.ZIPCode());
                userOrders.setProvince(req.province());
                userOrders.setAddress(req.address());
                userOrders.setEmail(req.email());
                userOrders.setPhoneNumber(req.phoneNumber());
                userOrders.setProducts(userCartItems);
                userOrders.setOrderDate(dateTime);
                userOrders.setDeliveryDate(dateTime.plusDays(7));
                userOrders.setTotal(userOrders.calculateTotal(userCartItems));

                List<CompletedOrders> userOrderItems = userCartItems.stream()
                        .map(cartItem -> {
                            CompletedOrders order = new CompletedOrders();
                            order.setUserId(user);
                            order.setProductId(cartItem.getProductId());
                            order.setProductSize(cartItem.getProductSize());
                            order.setQuantity(cartItem.getQuantity());
                            order.setOrder(userOrders);
                            order.setPrice(cartItem.getPrice());
                            return order;
                        })
                        .toList();

                user.getCompletedOrders().addAll(userOrderItems);
                user.getOrders().add(userOrders);
                user.getUserCart().clear(); // Clear the cart list in memory

                // Save the UserOrders entity
                userAccountRepository.save(user);

                cartRepository.deleteByUserId(user);

                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }
}
