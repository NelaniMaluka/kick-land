package com.examplekicklaandwebsite.KickLaand.service;

import com.examplekicklaandwebsite.KickLaand.dto.OrderRequest;
import org.springframework.http.ResponseEntity;

public interface OrderService {
	ResponseEntity<?> createOrder(OrderRequest req) throws Exception;
	ResponseEntity<?> getOrder(Integer userId);
}
