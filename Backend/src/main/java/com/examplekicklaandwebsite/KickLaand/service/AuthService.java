package com.examplekicklaandwebsite.KickLaand.service;

import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> login(UserAccount userAccount);

    ResponseEntity<?> createAccount(UserAccount userAccount);
}
