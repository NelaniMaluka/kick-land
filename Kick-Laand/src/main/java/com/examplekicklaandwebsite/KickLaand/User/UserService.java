package com.examplekicklaandwebsite.KickLaand.User;

import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface UserService {
    ResponseEntity<?> login(UserAccount userAccount);
    ResponseEntity<?> createAccount(UserAccount userAccount);
    ResponseEntity<?> updateUserFields(Integer userId, Map<String, String> updates);
    ResponseEntity<String> forgotPassword(UserAccount userAccount);
    ResponseEntity<String> resetPassword(Map<String, String> requestBody);
}

