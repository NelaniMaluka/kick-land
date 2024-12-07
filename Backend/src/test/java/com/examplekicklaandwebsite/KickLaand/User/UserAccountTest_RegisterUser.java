package com.examplekicklaandwebsite.KickLaand.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.any;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.examplekicklaandwebsite.KickLaand.controller.UserController;
import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserAccountTest_RegisterUser {

    @Mock
    private UserService userServiceMock;

    @InjectMocks
    private UserController userAccountController;

    // Edge case tests for valid emails and passwords
    @Test
    void testValidUserAccount() {
        // Arrange
        String email = "validemail@gmail.com";
        String password = "ValidP@ssw0rd";
        UserAccount userAccount = UserAccount.builder()
                .firstname("John")
                .lastname("Doe")
                .email(email)
                .password(password)
                .build();

        // Mock the service layer to return a successful response
        doReturn(ResponseEntity.ok("Successfully registered user"))
                .when(userServiceMock).createAccount(any(UserAccount.class));

        // Act
        ResponseEntity<?> response = userAccountController.createAccount(userAccount);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully registered user", response.getBody());
    }

    @Test
    void testInvalidEmail() {
        // Arrange
        String email = "invalidEmail";
        String password = "ValidP@ssw0rd";
        UserAccount userAccount = UserAccount.builder()
                .firstname("John")
                .lastname("Doe")
                .email(email)
                .password(password)
                .build();

        // Mock the service layer to return a bad request for invalid email
        doReturn(ResponseEntity.badRequest().body("Invalid email and/or password"))
                .when(userServiceMock).createAccount(any(UserAccount.class));

        // Act
        ResponseEntity<?> response = userAccountController.createAccount(userAccount);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid email and/or password", response.getBody());
    }

    @Test
    void testShortPassword() {
        // Arrange
        String email = "validemail@gmail.com";
        String password = "short";
        UserAccount userAccount = UserAccount.builder()
                .firstname("John")
                .lastname("Doe")
                .email(email)
                .password(password)
                .build();

        // Mock the service layer to return a bad request for short password
        doReturn(ResponseEntity.badRequest().body("Invalid email and/or password"))
                .when(userServiceMock).createAccount(any(UserAccount.class));

        // Act
        ResponseEntity<?> response = userAccountController.createAccount(userAccount);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid email and/or password", response.getBody());
    }

    @Test
    void testUserAlreadyExists() {
        // Arrange
        String email = "existing_email@gmail.com";
        UserAccount userAccount = UserAccount.builder()
                .firstname("John")
                .lastname("Doe")
                .email(email)
                .password("ValidP@ssw0rd")
                .build();

        // Simulating the response for existing email
        doReturn(ResponseEntity.badRequest().body("User with this email already exists"))
                .when(userServiceMock).createAccount(any(UserAccount.class));

        // Act
        ResponseEntity<?> response = userAccountController.createAccount(userAccount);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User with this email already exists", response.getBody());
    }

    @Test
    void testDatabaseError() {
        // Arrange
        UserAccount userAccount = UserAccount.builder()
                .firstname("John")
                .lastname("Doe")
                .email("testemail@gmail.com")
                .password("ValidP@ssw0rd")
                .build();

        // Simulate a database error
        doReturn(ResponseEntity.badRequest().body("An error occurred while creating the account"))
                .when(userServiceMock).createAccount(any(UserAccount.class));

        // Act
        ResponseEntity<?> response = userAccountController.createAccount(userAccount);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("An error occurred while creating the account", response.getBody());
    }
}
