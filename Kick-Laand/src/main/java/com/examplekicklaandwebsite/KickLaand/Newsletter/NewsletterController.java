package com.examplekicklaandwebsite.KickLaand.Newsletter;

import java.util.Optional;

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

    @PostMapping(path="/Backend/Newsletter")
    public ResponseEntity<?> addNewsletter(@RequestBody Newsletter newsletter ) {
 

        try {
        	Optional<Newsletter> existingNewsletter = Optional.ofNullable(newsletterRepository.findByEmail(newsletter.getEmail()));
            
            if (!existingNewsletter.isPresent()) {
                newsletterRepository.save(newsletter);
                return ResponseEntity.ok("We Received Your Email");
            }
        	
            return ResponseEntity.badRequest().body("Email Already Subscribed");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid Email");
        }
    }
}
