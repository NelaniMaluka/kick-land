package com.examplekicklaandwebsite.KickLaand.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/api/user/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserAccount userAccount) {
        return userService.login(userAccount);
    }

    @PostMapping(path = "/api/user/create-account")
    public ResponseEntity<?> createAccount(@Valid @RequestBody UserAccount userAccount) {
        return userService.createAccount(userAccount);
    }

    @PutMapping("/api/user/update-user/{userId}")
    public ResponseEntity<?> updateUserFields(@PathVariable @NonNull Integer userId, @Valid @RequestBody Map<String, String> updates) {
        return userService.updateUserFields(userId, updates);
    }

    @PostMapping("/api/user/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody UserAccount userAccount) {
        return userService.forgotPassword(userAccount);
    }
}
