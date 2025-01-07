package com.examplekicklaandwebsite.KickLaand.service;

import com.examplekicklaandwebsite.KickLaand.request.UserRequest;
import org.springframework.http.ResponseEntity;

import com.examplekicklaandwebsite.KickLaand.model.UserAccount;

public interface UserService {
    ResponseEntity<?> updateUserFields(UserAccount user, UserRequest req);

    UserAccount findUserByJwtToken(String jwt) throws Exception;
}
