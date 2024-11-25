package com.examplekicklaandwebsite.KickLaand.Newsletter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class NewsletterControllerTest {

    @Mock
    private NewsletterService newsletterServiceMock;

    @InjectMocks
    private NewsletterController newsletterController;

    @Test
    void sendSuccessfulEmail() throws Exception {
        // Arrange
        String email = "testemail@gmail.com";
        Newsletter newsletter = new Newsletter();
        newsletter.setEmail(email);

        // Using doReturn to mock the behavior of newsletterServiceMock.addNewsletter
        doReturn("We Received Your Email").when(newsletterServiceMock).addNewsletter(any(Newsletter.class));

        // Act
        ResponseEntity<?> responseEntity = newsletterController.addNewsletter(newsletter);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("We Received Your Email", responseEntity.getBody());
    }

    @Test
    void sendInvalidEmail_NullEmail() throws Exception {
        // Arrange
        Newsletter newsletter = new Newsletter();
        newsletter.setEmail(null);

        // Use doThrow to simulate an exception for null email
        doThrow(new IllegalArgumentException("Please Enter a Valid Email"))
            .when(newsletterServiceMock).addNewsletter(any(Newsletter.class));

        // Act
        ResponseEntity<?> responseEntity = newsletterController.addNewsletter(newsletter);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Please Enter a Valid Email", responseEntity.getBody());
    }

    @Test
    void sendDuplicateEmail() throws Exception {
        // Arrange
        String email = "existing_email@gmail.com";
        Newsletter newsletter = new Newsletter();
        newsletter.setEmail(email);

        // Use doThrow to simulate an exception for duplicate email
        doThrow(new IllegalStateException("Email Already Subscribed"))
            .when(newsletterServiceMock).addNewsletter(any(Newsletter.class));

        // Act
        ResponseEntity<?> responseEntity = newsletterController.addNewsletter(newsletter);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Email Already Subscribed", responseEntity.getBody());
    }

    @Test
    void sendInvalidEmail_InvalidFormat() throws Exception {
        // Arrange
        String email = "invalid_email"; // Missing @ and domain
        Newsletter newsletter = new Newsletter();
        newsletter.setEmail(email);

        // Use doThrow to simulate an exception for invalid format
        doThrow(new IllegalArgumentException("Invalid Email Format"))
            .when(newsletterServiceMock).addNewsletter(any(Newsletter.class));

        // Act
        ResponseEntity<?> responseEntity = newsletterController.addNewsletter(newsletter);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid Email Format", responseEntity.getBody());
    }
}
