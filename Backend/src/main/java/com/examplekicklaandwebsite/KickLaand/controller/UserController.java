package com.examplekicklaandwebsite.KickLaand.controller;

import com.examplekicklaandwebsite.KickLaand.util.createUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.service.UserService;

import jakarta.validation.Valid;

import java.util.Map;

@RestController
@Validated
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-details")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String jwt) throws Exception {
        UserAccount user = userService.findUserByJwtToken(jwt);
        return ResponseEntity.ok(createUserResponse.createResponse(user));
    }

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUserFields(@RequestHeader("Authorization") String jwt,
            @Valid @RequestBody Map<String, String> updates) throws Exception {
        UserAccount user = userService.findUserByJwtToken(jwt);
        return userService.updateUserFields(user, updates);
    }

}
