package com.examplekicklaandwebsite.KickLaand.service;

import com.examplekicklaandwebsite.KickLaand.request.OrderReq;
import com.examplekicklaandwebsite.KickLaand.request.OrderRequest;
import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import org.springframework.http.ResponseEntity;

public interface OrderService {
	ResponseEntity<?> generatePaymentLink(OrderRequest req, UserAccount user);
	ResponseEntity<?> confirmAndCreateOrder(OrderReq req, UserAccount user);
	ResponseEntity<?> getOrder(UserAccount user);
}
