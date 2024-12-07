package com.examplekicklaandwebsite.KickLaand.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.examplekicklaandwebsite.KickLaand.util.FormValidation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.examplekicklaandwebsite.KickLaand.controller.UserController;
import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserAccountTest_Login {

    @Mock
    private UserService userServiceMock;

    @ParameterizedTest
    @CsvSource({
            "Testemail@gmail.com, P@ssw0rd17", // Valid credentials
            "invalidEmail, P@ssw0rd17", // Invalid email
            "Testemail@gmail.com, short", // Invalid password
            "'', P@ssw0rd17", // Missing email
            "Testemail@gmail.com, ''" // Missing password
    })
    void sendUserDetails_Login(String email, String password) {
        UserAccount userAccount = UserAccount.builder()
                .email(FormValidation.toNullIfEmpty(email))
                .password(FormValidation.toNullIfEmpty(password))
                .build();

        when(userServiceMock.login(any(UserAccount.class))).thenAnswer(invocation -> {
            UserAccount acc = invocation.getArgument(0);
            if (acc.getEmail() == null || !FormValidation.isValidEmail(acc.getEmail()) ||
                    acc.getPassword() == null || !FormValidation.isValidPassword(acc.getPassword())) {
                return ResponseEntity.badRequest().body("Invalid credentials");
            }
            return ResponseEntity.ok("Login successful");
        });

        ResponseEntity<?> response = userServiceMock.login(userAccount);

        if (email == null || email.isEmpty() || !FormValidation.isValidEmail(email) ||
                password == null || password.isEmpty() || !FormValidation.isValidPassword(password)) {
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertEquals("Invalid credentials", response.getBody());
        } else {
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("Login successful", response.getBody());
        }
    }

}
