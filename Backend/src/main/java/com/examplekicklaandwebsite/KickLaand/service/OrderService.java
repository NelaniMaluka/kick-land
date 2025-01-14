package com.examplekicklaandwebsite.KickLaand.service;

import com.examplekicklaandwebsite.KickLaand.request.OrderRequest;
import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import org.springframework.http.ResponseEntity;

public interface OrderService {
	ResponseEntity<?> generatePaymentLink(OrderRequest req, UserAccount user);
	ResponseEntity<?> confirmAndCreateOrder(String paymentId, OrderRequest req, UserAccount user);
	ResponseEntity<?> getOrder(UserAccount user);
}
