package com.examplekicklaandwebsite.KickLaand.service.impl;

import com.examplekicklaandwebsite.KickLaand.dto.OrderRequest;
import com.examplekicklaandwebsite.KickLaand.model.CompletedOrders;
import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.model.UserCarts;
import com.examplekicklaandwebsite.KickLaand.model.UserOrders;
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

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserAccountRepository userAccountRepository;
    private final PaymentService paymentService;

    public OrderServiceImpl(UserAccountRepository userAccountRepository, PaymentService paymentService) {
        this.userAccountRepository = userAccountRepository;
        this.paymentService = paymentService;
    }

    public ResponseEntity<?> getOrder(Integer userId) {
        try {
            UserAccount user = userAccountRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));

                List<CompletedOrders> userOrders = user.getCompletedOrders();
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> createOrder(OrderRequest req) throws Exception {
        try {
            UserAccount user = userAccountRepository.findById(req.userId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + req.userId()));

            List<UserCarts> userCartItems = user.getUserCart();

            if (userCartItems.isEmpty()){
                return ResponseEntity.ok(null);
            } else {

                // Creating the payment link via the service
                PaymentResponse response = paymentService.createPaymentLink(userCartItems);

                // Create the UserOrders entity
                UserOrders userOrders = UserOrders.builder()
                        .userId(user)
                        .userAddress(req.userAddress())
                        .userEmail(req.userEmail())
                        .userPhoneNumber(req.userPhoneNumber())
                        .products(userCartItems)
                        .build();

                List<CompletedOrders> userOrderItems = userCartItems.stream()
                        .map(cartItem -> CompletedOrders.builder()
                                .userId(user)
                                .productId(cartItem.getProductId())
                                .productSize(cartItem.getProductSize())
                                .quantity(cartItem.getQuantity())
                                .order(userOrders)
                                .price(cartItem.getPrice())
                                .build())
                        .toList();

                user.getCompletedOrders().addAll(userOrderItems);
                user.getOrders().add(userOrders);
                user.userCart.clear();
                // Save the UserOrders entity
                userAccountRepository.save(user);

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
