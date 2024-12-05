package com.examplekicklaandwebsite.KickLaand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.service.UserService;

import jakarta.validation.Valid;

import java.util.Map;

@RestController
@Validated
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserAccount userAccount) {
        return userService.login(userAccount);
    }

    @PostMapping(path = "/create-account")
    public ResponseEntity<?> createAccount(@Valid @RequestBody UserAccount userAccount) {
        return userService.createAccount(userAccount);
    }

    @PutMapping("/update-user/{userId}")
    public ResponseEntity<?> updateUserFields(@PathVariable @NonNull Integer userId,
            @Valid @RequestBody Map<String, String> updates) {
        return userService.updateUserFields(userId, updates);
    }

}
