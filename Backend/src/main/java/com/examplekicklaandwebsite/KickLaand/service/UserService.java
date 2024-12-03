package com.examplekicklaandwebsite.KickLaand.service;

import org.springframework.http.ResponseEntity;

import com.examplekicklaandwebsite.KickLaand.model.UserAccount;

import java.util.Map;

public interface UserService {
    ResponseEntity<?> login(UserAccount userAccount);
    ResponseEntity<?> createAccount(UserAccount userAccount);
    ResponseEntity<?> updateUserFields(Integer userId, Map<String, String> updates);
    ResponseEntity<String> forgotPassword(String email);
}

