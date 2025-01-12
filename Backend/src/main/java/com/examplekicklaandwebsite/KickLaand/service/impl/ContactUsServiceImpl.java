package com.examplekicklaandwebsite.KickLaand.service.impl;

import com.examplekicklaandwebsite.KickLaand.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examplekicklaandwebsite.KickLaand.model.ContactUs;
import com.examplekicklaandwebsite.KickLaand.repository.ContactUsRepository;
import com.examplekicklaandwebsite.KickLaand.service.ContactUsService;
import com.examplekicklaandwebsite.KickLaand.util.FormValidation;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    private final ContactUsRepository contactUsRepository;

    public ContactUsServiceImpl(ContactUsRepository contactUsRepository) {
        this.contactUsRepository = contactUsRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<?> sendInfo(ContactUs contactUs) {
        try {
            // Trim and validate fields
            String name = trimAndValidate(contactUs.getName(), "Name");
            String email = trimAndValidate(contactUs.getEmail(), "Email");
            String phoneNumber = trimAndValidate(contactUs.getPhoneNumber(), "Phone Number");
            String message = trimAndValidate(contactUs.getMessage(), "Message");

            if (!FormValidation.isValidEmail(email)) {
                return ResponseEntity.badRequest()
                        .body(new ErrorResponse("Invalid email format", "The provided email address is not valid. Please provide a valid email address."));
            }

            if (!FormValidation.isValidPhonenumber(phoneNumber)) {
                return ResponseEntity.badRequest()
                        .body(new ErrorResponse("Invalid phone number format", "The provided phone number is not valid. Please provide a phone number containing 10 to 15 digits."));
            }


            contactUs.setName(name);
            contactUs.setEmail(email);
            contactUs.setPhoneNumber(phoneNumber);
            contactUs.setMessage(message);

            // Save the contact message
            contactUsRepository.save(contactUs);
            return ResponseEntity.ok("We received your message. Thank you for reaching out.");
        } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ErrorResponse("Internal Server Error", "An unexpected error occurred while saving your message. Please try again later."));
            }
        }

    private String trimAndValidate(String field, String fieldName) {
        if (field == null || field.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }
        return field.trim();
    }

}
