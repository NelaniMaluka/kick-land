package com.examplekicklaandwebsite.KickLaand.Newsletter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NewsletterTest {

    @Mock
    NewsletterRepository newsletterRepositoryMock;

    @InjectMocks
    private NewsletterController newsletterController;

    @Test
    void sendSuccessfulEmail() {
        String email = "testemail@gmail.com";

        Newsletter newsletter = new Newsletter();
        newsletter.setEmail(email);

        when(newsletterRepositoryMock.findByEmail(email)).thenReturn(null);

        ResponseEntity<?> responseEntity = newsletterController.addNewsletter(newsletter);

        assertEquals(ResponseEntity.ok("We Received Your Email"), responseEntity);
    }

    @Test
    void sendInvalidEmail() {
        String email = null;

        Newsletter newsletter = new Newsletter();
        newsletter.setEmail(email);

        when(newsletterRepositoryMock.findByEmail(email)).thenReturn(null);

        ResponseEntity<?> responseEntity = newsletterController.addNewsletter(newsletter);
        System.out.println(responseEntity);
        assertEquals(ResponseEntity.badRequest().body("Invalid Email"), responseEntity);
    }
}
