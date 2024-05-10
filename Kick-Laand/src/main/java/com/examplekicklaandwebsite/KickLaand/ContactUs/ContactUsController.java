package com.examplekicklaandwebsite.KickLaand.ContactUs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactUsController {

    private final ContactUsService contactUsService;

    public ContactUsController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }

    @PostMapping(path = "/api/public/contactUs")
    public ResponseEntity<String> sendInfo(@RequestBody ContactUs contactUs) {
        try {
            String response = contactUsService.sendInfo(contactUs);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
