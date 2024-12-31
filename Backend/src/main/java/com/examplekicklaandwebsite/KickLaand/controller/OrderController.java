package com.examplekicklaandwebsite.KickLaand.controller;

import com.examplekicklaandwebsite.KickLaand.request.OrderRequest;
import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.service.OrderService;

import com.examplekicklaandwebsite.KickLaand.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/order")
    public ResponseEntity<?> CreateOrder(@RequestHeader("Authorization") String jwt, @Valid @RequestBody OrderRequest req) throws Exception {
        UserAccount user = userService.findUserByJwtToken(jwt);
        return orderService.createOrder(req, user);
    }

    @GetMapping("/order")
    public ResponseEntity<?> GetOrder(@RequestHeader("Authorization") String jwt) throws Exception {
        UserAccount user = userService.findUserByJwtToken(jwt);
        return orderService.getOrder(user);
    }

}
