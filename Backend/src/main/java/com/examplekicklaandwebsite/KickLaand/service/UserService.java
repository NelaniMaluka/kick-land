package com.examplekicklaandwebsite.KickLaand.service;

import org.springframework.http.ResponseEntity;

import com.examplekicklaandwebsite.KickLaand.model.UserAccount;

import java.util.Map;

public interface UserService {
    ResponseEntity<?> login(UserAccount userAccount);
    ResponseEntity<?> createAccount(UserAccount userAccount);
    ResponseEntity<?> updateUserFields(UserAccount user, Map<String, String> updates);

    public UserAccount findUserByJwtToken(String jwt) throws Exception;

    public UserAccount findUserByEmail(String email) throws Exception;
}

