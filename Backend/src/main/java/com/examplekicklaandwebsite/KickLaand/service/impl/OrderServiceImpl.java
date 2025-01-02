package com.examplekicklaandwebsite.KickLaand.service.impl;

import com.examplekicklaandwebsite.KickLaand.request.OrderRequest;
import com.examplekicklaandwebsite.KickLaand.model.CompletedOrders;
import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.model.UserCarts;
import com.examplekicklaandwebsite.KickLaand.model.UserOrders;
import com.examplekicklaandwebsite.KickLaand.repository.CartRepository;
import com.examplekicklaandwebsite.KickLaand.repository.UserAccountRepository;
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

    public ResponseEntity<?> getOrder(UserAccount user) {
        try {

            List<UserOrders> userOrders = user.getOrders();
            if (userOrders.isEmpty()) {
                return ResponseEntity.ok(null);
            } else {
                Object filteredCartList = FilterLists.getFilteredOrderList(userOrders);
                return ResponseEntity.ok(filteredCartList);
            }

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data integrity violation");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
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
