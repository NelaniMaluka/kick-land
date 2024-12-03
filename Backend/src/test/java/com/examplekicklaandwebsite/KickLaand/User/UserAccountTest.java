package com.examplekicklaandwebsite.KickLaand.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
public class UserAccountTest {

    @Mock
    private UserService userServiceMock;

    @InjectMocks
    private UserController userController;

    @ParameterizedTest
    @CsvSource({
            "Testemail@gmail.com, P@ssw0rd", // Valid credentials
            "invalidEmail, P@ssw0rd", // Invalid email
            "Testemail@gmail.com, short", // Invalid password
            "'', P@ssw0rd", // Missing email
            "Testemail@gmail.com, ''" // Missing password
    })
    void sendUserDetails_Login_ForgotPassword(String email, String password) {
        UserAccount userAccount = new UserAccount(toNullIfEmpty(email), toNullIfEmpty(password));

        when(userServiceMock.login(any(UserAccount.class))).thenAnswer(invocation -> {
            UserAccount acc = invocation.getArgument(0);
            if (acc.getEmail() == null || !isValidEmail(acc.getEmail()) ||
                    acc.getPassword() == null || !isValidPassword(acc.getPassword())) {
                return ResponseEntity.badRequest().body("Invalid credentials");
            }
            return ResponseEntity.ok("Login successful");
        });

        ResponseEntity<?> response = userServiceMock.login(userAccount);

        if (email == null || email.isEmpty() || !isValidEmail(email) ||
                password == null || password.isEmpty() || !isValidPassword(password)) {
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertEquals("Invalid credentials", response.getBody());
        } else {
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("Login successful", response.getBody());
        }
    }

    @ParameterizedTest
    @CsvSource({
            "Testemail@gmail.com", // Valid email
            "nwe", // Invalid email
            "'',", // Empty email
            "null" // Null email
    })
    void sendUserDetails_ResetPassword(String email) {
        UserAccount userAccount = new UserAccount(toNullIfEmpty(email));

        when(userServiceMock.forgotPassword(any(UserAccount.class))).thenAnswer(invocation -> {
            UserAccount userAcc = invocation.getArgument(0);
            if (userAcc.getEmail() == null || !isValidEmail(userAcc.getEmail())) {
                return ResponseEntity.badRequest().body("Email required");
            }
            return ResponseEntity.ok("Successfully sent reset password");
        });

        ResponseEntity<?> response = userServiceMock.forgotPassword(userAccount);

        if (email == null || email.isEmpty() || !isValidEmail(email)) {
            assertEquals("Email required", response.getBody());
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        } else {
            assertEquals("Successfully sent reset password", response.getBody());
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }

    private String toNullIfEmpty(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value;
    }

    private boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    private boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";
        return password.matches(passwordRegex);
    }
}
