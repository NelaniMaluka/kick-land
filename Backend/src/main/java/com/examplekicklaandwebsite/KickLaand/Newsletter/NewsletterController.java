package com.examplekicklaandwebsite.KickLaand.Newsletter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsletterController {

    private final NewsletterService newsletterService;

    public NewsletterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }

    @PostMapping(path = "/api/public/newsletter")
    public ResponseEntity<String> addNewsletter(@RequestBody Newsletter newsletter) {
        try {
            String response = newsletterService.addNewsletter(newsletter);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Exception handler to catch exceptions thrown by the controller
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body("An error occurred: " + ex.getMessage());
    }
}


