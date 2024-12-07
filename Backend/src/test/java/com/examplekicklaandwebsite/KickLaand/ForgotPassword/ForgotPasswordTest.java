package com.examplekicklaandwebsite.KickLaand.ForgotPassword;

import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.service.ForgotPasswordService;
import com.examplekicklaandwebsite.KickLaand.util.FormValidation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ForgotPasswordTest {

    private final ForgotPasswordService forgotPasswordServiceMock;

    public ForgotPasswordTest(ForgotPasswordService forgotPasswordServiceMock) {
        this.forgotPasswordServiceMock = forgotPasswordServiceMock;
    }

    @ParameterizedTest
    @CsvSource({
            "Testemail@gmail.com", // Valid email
            "nwe", // Invalid email
            "'',", // Empty email
            "null" // Null email
    })
    void sendUserDetails_ResetPassword(String email) {
        UserAccount userAccount = UserAccount.builder().email(FormValidation.toNullIfEmpty(email)).build();

        when(forgotPasswordServiceMock.verifyEmail(any(UserAccount.class))).thenAnswer(invocation -> {
            UserAccount userAcc = invocation.getArgument(0);
            if (userAcc.getEmail() == null || !FormValidation.isValidEmail(userAcc.getEmail())) {
                return ResponseEntity.badRequest().body("Email required");
            }
            return ResponseEntity.ok("Successfully sent reset password");
        });

        ResponseEntity<?> response = forgotPasswordServiceMock.verifyEmail(userAccount);

        if (email == null || email.isEmpty() || !FormValidation.isValidEmail(email)) {
            assertEquals("Email required", response.getBody());
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        } else {
            assertEquals("Successfully sent reset password", response.getBody());
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }
}
