package com.examplekicklaandwebsite.KickLaand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examplekicklaandwebsite.KickLaand.model.ContactUs;
import com.examplekicklaandwebsite.KickLaand.service.ContactUsService;

@RestController
@RequestMapping("/contact-us")
public class ContactUsController {

    private final ContactUsService contactUsService;

    @Autowired
    public ContactUsController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }

    @PostMapping()
    public ResponseEntity<String> sendContactUsMessage(@RequestBody ContactUs contactUs) {
        try {
            String response = contactUsService.sendInfo(contactUs);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) { // Catch validation exceptions
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

}
