package com.examplekicklaandwebsite.KickLaand.service;

import com.examplekicklaandwebsite.KickLaand.dto.OrderRequest;
import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import org.springframework.http.ResponseEntity;

public interface OrderService {
	ResponseEntity<?> createOrder(OrderRequest req, UserAccount user) throws Exception;
	ResponseEntity<?> getOrder(UserAccount user);
}
