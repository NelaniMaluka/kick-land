package com.examplekicklaandwebsite.KickLaand.Newsletter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class NewsletterTest {

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

        when(newsletterServiceMock.addNewsletter(newsletter)).thenReturn("We Received Your Email");

        // Act
        ResponseEntity<?> responseEntity = newsletterController.addNewsletter(newsletter);

        // Assert
        assertEquals(ResponseEntity.ok("We Received Your Email"), responseEntity);
    }

    @Test
    void sendInvalidEmail_NullEmail() throws Exception {
        // Arrange
        Newsletter newsletter = new Newsletter();
        newsletter.setEmail(null);

        when(newsletterServiceMock.addNewsletter(newsletter))
            .thenThrow(new IllegalArgumentException("Please Enter a Valid Email"));

        // Act
        ResponseEntity<?> responseEntity = newsletterController.addNewsletter(newsletter);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Please Enter a Valid Email"), responseEntity);
    }

    @Test
    void sendDuplicateEmail() throws Exception {
        // Arrange
        String email = "existing_email@gmail.com";
        Newsletter newsletter = new Newsletter();
        newsletter.setEmail(email);

        when(newsletterServiceMock.addNewsletter(newsletter))
            .thenThrow(new IllegalArgumentException("Email Already Subscribed"));

        // Act
        ResponseEntity<?> responseEntity = newsletterController.addNewsletter(newsletter);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Email Already Subscribed"), responseEntity);
    }

    @Test
    void sendInvalidEmail_InvalidFormat() throws Exception {
        // Arrange
        String email = "invalid_email";
        Newsletter newsletter = new Newsletter();
        newsletter.setEmail(email);

        when(newsletterServiceMock.addNewsletter(newsletter))
            .thenThrow(new IllegalArgumentException("Invalid Email"));

        // Act
        ResponseEntity<?> responseEntity = newsletterController.addNewsletter(newsletter);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Invalid Email"), responseEntity);
    }
}
