package com.examplekicklaandwebsite.KickLaand.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examplekicklaandwebsite.KickLaand.model.ContactUs;
import com.examplekicklaandwebsite.KickLaand.response.ErrorResponse;
import com.examplekicklaandwebsite.KickLaand.service.ContactUsService;

@RestController
@RequestMapping("/contact-us")
public class ContactUsController {

    private final ContactUsService contactUsService;

    public ContactUsController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }

    @PostMapping
    public ResponseEntity<?> sendContactUsMessage(@RequestBody ContactUs contactUs) {
        try {
            return contactUsService.sendInfo(contactUs);
        } catch (IllegalArgumentException e) { // Catch validation exceptions
            ErrorResponse errorResponse = new ErrorResponse("Validation Error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
                    "An unexpected error occurred while saving your message. Please try again later.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
