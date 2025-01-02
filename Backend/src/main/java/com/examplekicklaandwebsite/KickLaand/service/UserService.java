package com.examplekicklaandwebsite.KickLaand.service;

import org.springframework.http.ResponseEntity;

import com.examplekicklaandwebsite.KickLaand.model.UserAccount;

import java.util.Map;

public interface UserService {
    ResponseEntity<?> updateUserFields(UserAccount user, Map<String, String> updates);
    UserAccount findUserByJwtToken(String jwt) throws Exception;
}

