package com.examplekicklaandwebsite.KickLaand.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examplekicklaandwebsite.KickLaand.model.Newsletter;
import com.examplekicklaandwebsite.KickLaand.repository.NewsletterRepository;
import com.examplekicklaandwebsite.KickLaand.service.NewsletterService;
import com.examplekicklaandwebsite.KickLaand.util.FormValidation;

@Service
public class NewsletterServiceImpl implements NewsletterService {

    private final NewsletterRepository newsletterRepository;

    public NewsletterServiceImpl(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<String> addNewsletter(Newsletter newsletter) {
        try {
            String email = (newsletter.getEmail() != null) ? newsletter.getEmail().trim() : null;

            // Validate Email
            if (email == null || email.isEmpty()) {
                return new ResponseEntity<>("Please Enter a Valid Email", HttpStatus.BAD_REQUEST); // 400 Bad Request
            }

            // Validate Email
            if (!FormValidation.isValidEmail(email)) {
                return new ResponseEntity<>("Invalid Email Format", HttpStatus.BAD_REQUEST); // 400 Bad Request
            }

            // Check if email is already subscribed
            if (newsletterRepository.findByEmail(email) != null) {
                return new ResponseEntity<>("Email Already Subscribed", HttpStatus.CONFLICT); // 409 Conflict
            }

            // Save Newsletter
            newsletterRepository.save(newsletter);

            return new ResponseEntity<>("We Received Your Email", HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            // General server error
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }
}
