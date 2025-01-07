package com.examplekicklaandwebsite.KickLaand.controller;

import com.examplekicklaandwebsite.KickLaand.request.UserRequest;
import com.examplekicklaandwebsite.KickLaand.util.createUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.service.UserService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String jwt) throws Exception {
        UserAccount user = userService.findUserByJwtToken(jwt);
        return ResponseEntity.ok(createUserResponse.createResponse(user));
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUserFields(@RequestHeader("Authorization") String jwt,
            @Valid @RequestBody UserRequest req) throws Exception {
        UserAccount user = userService.findUserByJwtToken(jwt);
        return userService.updateUserFields(user, req);
    }

}
