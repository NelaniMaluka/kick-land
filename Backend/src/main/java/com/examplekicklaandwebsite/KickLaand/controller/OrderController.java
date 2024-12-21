package com.examplekicklaandwebsite.KickLaand.controller;

import com.examplekicklaandwebsite.KickLaand.dto.OrderRequest;
import com.examplekicklaandwebsite.KickLaand.service.OrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<?> CreateOrder(@Valid @RequestBody OrderRequest req) throws Exception {
        return orderService.createOrder(req);
    }

    @GetMapping("/order")
    public ResponseEntity<?> GetOrder(@Valid @RequestParam Integer userId) {
        return orderService.getOrder(userId);
    }

}
