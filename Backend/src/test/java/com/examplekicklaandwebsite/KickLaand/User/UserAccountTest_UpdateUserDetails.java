package com.examplekicklaandwebsite.KickLaand.User;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import com.examplekicklaandwebsite.KickLaand.controller.UserController;
import com.examplekicklaandwebsite.KickLaand.response.UserResponse;
import com.examplekicklaandwebsite.KickLaand.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class UserAccountTest_UpdateUserDetails {

    @InjectMocks
    private UserController userAccountController; // Inject the controller

    @Mock
    private UserServiceImpl userService; // Mocked service layer

    public UserAccountTest_UpdateUserDetails() {
        openMocks(this);
    }

    @SuppressWarnings("null")
    @Test
    public void testUpdateUserDetails_ValidUpdates() {
        // Arrange
        Integer userId = 1;
        Map<String, String> updates = new HashMap<>();
        updates.put("username", "NewName");
        updates.put("email", "newemail@example.com");
        updates.put("surname", "NewSurname");
        updates.put("phonenumber", "1234567890");
        updates.put("address", "New Address");

        // Mock the updated user response after updates
        UserResponse userResponseDTO = new UserResponse(
                userId, "NewName", "NewSurname", "newemail@example.com", "1234567890", "New Address");

        // Ensure the service method returns the mocked response
        doReturn(ResponseEntity.ok(userResponseDTO)).when(userService).updateUserFields(userId, updates);

        // Act
        ResponseEntity<?> response = userAccountController.updateUserFields(userId, updates);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        UserResponse responseBody = (UserResponse) response.getBody();
        assert responseBody != null;
        assertEquals("NewName", responseBody.getFirstname());
        assertEquals("NewSurname", responseBody.getLastname());
        assertEquals("newemail@example.com", responseBody.getEmail()); // Ensure email matches
        assertEquals("1234567890", responseBody.getPhoneNumber());
        assertEquals("New Address", responseBody.getAddress());

        verify(userService, times(1)).updateUserFields(userId, updates);
    }

    @Test
    public void testUpdateUserDetails_UserNotFound() {
        // Arrange
        Integer userId = 2;
        Map<String, String> updates = new HashMap<>();
        updates.put("username", "AnyName");

        // Using doReturn instead of when(...).thenReturn(...)
        doReturn(ResponseEntity.status(404).build()).when(userService).updateUserFields(userId, updates);

        // Act
        ResponseEntity<?> response = userAccountController.updateUserFields(userId, updates);

        // Assert
        assertEquals(404, response.getStatusCode().value());
        verify(userService, times(1)).updateUserFields(userId, updates);
    }

    @Test
    public void testUpdateUserDetails_InvalidField() {
        // Arrange
        Integer userId = 3;
        Map<String, String> updates = new HashMap<>();
        updates.put("invalidField", "value");

        // Using doReturn instead of when(...).thenReturn(...)
        doReturn(ResponseEntity.status(400).body("Invalid field specified")).when(userService).updateUserFields(userId,
                updates);

        // Act
        ResponseEntity<?> response = userAccountController.updateUserFields(userId, updates);

        // Assert
        assertEquals(400, response.getStatusCode().value());
        assertEquals("Invalid field specified", response.getBody());
        verify(userService, times(1)).updateUserFields(userId, updates);
    }

    @Test
    public void testUpdateUserDetails_ExceptionHandling() {
        // Arrange
        Integer userId = 4;
        Map<String, String> updates = new HashMap<>();
        updates.put("username", "NewName");

        // Using doReturn instead of when(...).thenReturn(...)
        doReturn(ResponseEntity.status(400).body("Failed to update user fields")).when(userService)
                .updateUserFields(userId, updates);

        // Act
        ResponseEntity<?> response = userAccountController.updateUserFields(userId, updates);

        // Assert
        assertEquals(400, response.getStatusCode().value());
        assertEquals("Failed to update user fields", response.getBody());
        verify(userService, times(1)).updateUserFields(userId, updates);
    }
}
