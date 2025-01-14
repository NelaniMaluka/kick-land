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

    public ResponseEntity<?> generatePaymentLink(OrderRequest req, UserAccount user) {
        try {
            List<UserCarts> userCartItems = user.getUserCart();

            if (userCartItems.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cart is empty");
            }

            // Call payment service to generate the payment link
            PaymentResponse response = paymentService.createPaymentLink(userCartItems);

            // Return the payment link to the user
            return ResponseEntity.ok(response); // Include paymentId and paymentUrl
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate payment link: " + ex.getMessage());
        }
    }

    public ResponseEntity<?> confirmAndCreateOrder(String sessionId, OrderRequest req, UserAccount user) {
        try {
            // Verify the payment using the sessionId from Stripe
            boolean isPaymentSuccessful = paymentService.verifyPayment(sessionId);  // Use the new verifyPayment method

            if (isPaymentSuccessful) {
                List<UserCarts> userCartItems = user.getUserCart();
                LocalDateTime dateTime = LocalDateTime.now();

                // Create UserOrders entity
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

                // Map user carts to completed orders
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
                user.getUserCart().clear(); // Clear the cart after order is created

                // Save to database
                userAccountRepository.save(user);
                cartRepository.deleteByUserId(user);

                return ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment verification failed.");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create order: " + ex.getMessage());
        }
    }


}
