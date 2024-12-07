package com.examplekicklaandwebsite.KickLaand.ContactUs;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.examplekicklaandwebsite.KickLaand.controller.ContactUsController;
import com.examplekicklaandwebsite.KickLaand.model.ContactUs;
import com.examplekicklaandwebsite.KickLaand.service.ContactUsService;

@ExtendWith(MockitoExtension.class)
class ContactUsControllerTest {

    @Mock
    private ContactUsService contactUsServiceMock;

    @InjectMocks
    private ContactUsController contactUsController;

    // 1. Valid input test case
    @Test
    void sendContactUsMessage_ValidInput_ShouldReturnSuccessMessage() throws Exception {
        ContactUs contactUs = ContactUs.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .phoneNumber("+27876543209")
                .message("This is a test message")
                .build();

        // Mock service response for valid input
        when(contactUsServiceMock.sendInfo(contactUs)).thenReturn("We Received your message");

        ResponseEntity<String> response = contactUsController.sendContactUsMessage(contactUs);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("We Received your message", response.getBody());
    }

    // 2. Missing fields test
    @ParameterizedTest
    @CsvSource({
            "'', john.doe@example.com, +27876543209, Test message", // Missing name
            "John Doe, '', +27876543209, Test message", // Missing email
            "John Doe, john.doe@example.com, '', Test message", // Missing phone number
            "John Doe, john.doe@example.com, +27876543209, ''" // Missing message
    })
    void sendContactUsMessage_MissingFields_ShouldReturnBadRequest(String name, String email, String phoneNumber,
            String message) throws Exception {
        ContactUs contactUs = ContactUs.builder()
                .name(toNullIfEmpty(name))
                .email(toNullIfEmpty(email))
                .phoneNumber(toNullIfEmpty(phoneNumber))
                .message(toNullIfEmpty(message))
                .build();

        // Simulate exception for missing fields
        doThrow(new IllegalArgumentException("All fields are required."))
                .when(contactUsServiceMock).sendInfo(any(ContactUs.class));

        ResponseEntity<String> response = contactUsController.sendContactUsMessage(contactUs);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("All fields are required.", response.getBody());
    }

    // 3. Invalid data format test
    @ParameterizedTest
    @CsvSource({
            "John Doe, invalid-email, +27876543209, Test message", // Invalid email format
            "John Doe, john.doe@example.com, invalid-phone, Test message" // Invalid phone number format
    })
    void sendContactUsMessage_InvalidFormat_ShouldReturnBadRequest(String name, String email, String phoneNumber,
            String message) throws Exception {
        ContactUs contactUs = ContactUs.builder()
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .message(message)
                .build();

        // Simulate exception for invalid format
        doThrow(new IllegalArgumentException("Invalid format."))
                .when(contactUsServiceMock).sendInfo(any(ContactUs.class));

        ResponseEntity<String> response = contactUsController.sendContactUsMessage(contactUs);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid format.", response.getBody());
    }

    // 4. Service exception test
    @Test
    void sendContactUsMessage_ServiceException_ShouldReturnInternalServerError() throws Exception {
        ContactUs contactUs = ContactUs.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .phoneNumber( "+27876543209")
                .message("Test message")
                .build();

        // Simulate service throwing an exception
        when(contactUsServiceMock.sendInfo(any(ContactUs.class))).thenThrow(new RuntimeException("Service error"));

        ResponseEntity<String> response = contactUsController.sendContactUsMessage(contactUs);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred", response.getBody());
    }

    // Helper method to convert empty strings to null
    private String toNullIfEmpty(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value;
    }
}
