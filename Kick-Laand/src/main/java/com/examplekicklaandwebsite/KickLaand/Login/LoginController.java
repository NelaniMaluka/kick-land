package com.examplekicklaandwebsite.KickLaand.Login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @PostMapping(path="/Backend/Login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
      
        if ("malukanelani@gmail.com".equals(loginRequest.getEmail()) && "password".equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(true);
        } else {
           return ResponseEntity.badRequest().body("Invalid Credentials");
        }
    }
}

