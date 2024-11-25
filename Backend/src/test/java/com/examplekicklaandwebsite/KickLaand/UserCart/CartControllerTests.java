package com.examplekicklaandwebsite.KickLaand.UserCart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // Using MockitoExtension for easy mock setup
public class CartControllerTests {

    @Mock
    private CartService cartServiceMock;  // Mocking CartService

    @InjectMocks
    private CartController cartController;  // Injecting the mock service into the controller

    private String validEmail = "test@example.com";  // A valid email for testing
    private UserCartDTO validCartDTO;  // A valid DTO for adding/updating the cart

    @BeforeEach
    public void setUp() {
        validCartDTO = new UserCartDTO(1, 1, 2, "M");  // Setting up a mock DTO before each test
    }

    @Test
    public void testGetUserCartItems_UserFound() {
        // Arrange
        doReturn(ResponseEntity.ok("Mocked Cart Items")).when(cartServiceMock).getUserCartItems(validEmail);

        // Act
        ResponseEntity<?> response = cartController.getUserCartItems(validEmail);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Mocked Cart Items", response.getBody());
    }

    @Test
    public void testGetUserCartItems_UserNotFound() {
        // Arrange
        doReturn(ResponseEntity.status(404).body("User not found")).when(cartServiceMock).getUserCartItems(validEmail);

        // Act
        ResponseEntity<?> response = cartController.getUserCartItems(validEmail);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("User not found", response.getBody());
    }

    @Test
    public void testAddToCart_Success() {
        // Arrange
        doReturn(ResponseEntity.ok("Cart updated successfully")).when(cartServiceMock).addToCart(validCartDTO);

        // Act
        ResponseEntity<?> response = cartController.addToCart(validCartDTO);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cart updated successfully", response.getBody());
    }

    @Test
    public void testAddToCart_Failure() {
        // Arrange
        doReturn(ResponseEntity.badRequest().body("Invalid cart item")).when(cartServiceMock).addToCart(validCartDTO);

        // Act
        ResponseEntity<?> response = cartController.addToCart(validCartDTO);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid cart item", response.getBody());
    }

    @Test
    public void testUpdateCart_Success() {
        // Arrange
        doReturn(ResponseEntity.ok("Cart item updated")).when(cartServiceMock).updateCart(1, 1, 3);

        // Act
        ResponseEntity<?> response = cartController.updateCart(1, 1, 3);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cart item updated", response.getBody());
    }

    @Test
    public void testDeleteCartItem_Success() {
        // Arrange
        doReturn(ResponseEntity.ok("Cart item deleted")).when(cartServiceMock).deleteCartItem(1, 1);

        // Act
        ResponseEntity<?> response = cartController.deleteCartItem(1, 1);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cart item deleted", response.getBody());
    }
}
