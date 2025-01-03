package com.examplekicklaandwebsite.KickLaand.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examplekicklaandwebsite.KickLaand.model.ContactUs;
import com.examplekicklaandwebsite.KickLaand.model.Newsletter;
import com.examplekicklaandwebsite.KickLaand.repository.ContactUsRepository;
import com.examplekicklaandwebsite.KickLaand.repository.NewsletterRepository;
import com.examplekicklaandwebsite.KickLaand.service.ContactUsService;
import com.examplekicklaandwebsite.KickLaand.util.FormValidation;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    private final ContactUsRepository contactUsRepository;
    private final NewsletterRepository newsletterRepository;

    public ContactUsServiceImpl(ContactUsRepository contactUsRepository, NewsletterRepository newsletterRepository) {
        this.contactUsRepository = contactUsRepository;
        this.newsletterRepository = newsletterRepository;
    }

    @Override
    @Transactional
    public String sendInfo(ContactUs contactUs) {
        try {
            // Trim and validate fields
            String name = trimAndValidate(contactUs.getName(), "Name");
            String email = trimAndValidate(contactUs.getEmail(), "Email");
            String phoneNumber = trimAndValidate(contactUs.getPhoneNumber(), "Phone Number");
            String message = trimAndValidate(contactUs.getMessage(), "Message");

            // Validate email format
            if (!FormValidation.isValidEmail(email)) {
                throw new IllegalArgumentException("Invalid email format.");
            }

            // Validate phone number format
            if (!FormValidation.isValidPhonenumber(phoneNumber)) {
                throw new IllegalArgumentException("Invalid phone number format. It should contain 10 to 15 digits.");
            }

            // Check if the email exists in the newsletter repository
            Newsletter existingNewsletter = newsletterRepository.findByEmail(email);
            if (existingNewsletter == null) {
                // Create a new newsletter entry
                Newsletter newsletter = new Newsletter();
                newsletter.setEmail(email);

                newsletterRepository.save(newsletter);
            }

            contactUs.setName(name);
            contactUs.setEmail(email);
            contactUs.setPhoneNumber(phoneNumber);
            contactUs.setMessage(message);

            // Save the contact message
            contactUsRepository.save(contactUs);
            return "We received your message.";
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    private String trimAndValidate(String field, String fieldName) {
        if (field == null || field.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }
        return field.trim();
    }

}
