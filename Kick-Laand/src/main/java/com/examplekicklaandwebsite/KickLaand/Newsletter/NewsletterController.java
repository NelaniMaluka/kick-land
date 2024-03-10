package com.examplekicklaandwebsite.KickLaand.Newsletter;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class NewsletterController {

    private final NewsletterRepository newsletterRepository;

    public NewsletterController(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }

    @PostMapping(path = "/api/public/newsletter")
    public ResponseEntity<?> addNewsletter(@Valid @RequestBody Newsletter newsletter) {
        try {
        	
            if (newsletter.getEmail() == null) {
                return ResponseEntity.badRequest().body("Please Enter a Valid Email");
            }
        	
            String trimmedEmail = newsletter.getEmail().trim();

            Optional<Newsletter> existingNewsletter = Optional.ofNullable(newsletterRepository.findByEmail(trimmedEmail));

            if (existingNewsletter.isPresent()) {
                return ResponseEntity.badRequest().body("Email Already Subscribed");
            }

            // Update the email in the original newsletter object after trimming
            newsletter.setEmail(trimmedEmail);
            
            // Save the newsletter
            newsletterRepository.save(newsletter);

            return ResponseEntity.ok("We Received Your Email");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid Email Format");
        }
    }
}