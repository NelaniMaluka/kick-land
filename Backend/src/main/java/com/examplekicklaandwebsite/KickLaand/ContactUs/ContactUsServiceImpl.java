package com.examplekicklaandwebsite.KickLaand.ContactUs;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.examplekicklaandwebsite.KickLaand.Newsletter.Newsletter;
import com.examplekicklaandwebsite.KickLaand.Newsletter.NewsletterRepository;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    private final ContactUsRepository contactUsRepository;
    private final NewsletterRepository newsletterRepository;
    private static final Logger logger = LoggerFactory.getLogger(ContactUsServiceImpl.class);

    // Email and phone validation patterns
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{10,15}$");

    public ContactUsServiceImpl(ContactUsRepository contactUsRepository, NewsletterRepository newsletterRepository) {
        this.contactUsRepository = contactUsRepository;
        this.newsletterRepository = newsletterRepository;
    }

    @Override
    @Transactional
    public String sendInfo(ContactUs contactUs) throws Exception {
        // Trim and validate fields
        String name = trimAndValidate(contactUs.getName(), "Name");
        String email = trimAndValidate(contactUs.getEmail(), "Email");
        String phoneNumber = trimAndValidate(contactUs.getPhoneNumber(), "Phone Number");
        String message = trimAndValidate(contactUs.getMessage(), "Message");

        // Validate email format
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        // Validate phone number format
        if (!PHONE_PATTERN.matcher(phoneNumber).matches()) {
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

        // Save the contact message
        contactUsRepository.save(new ContactUs(name, email, phoneNumber, message));
        logger.info("Contact message successfully saved for: {}", email);
        return "We received your message.";
    }

    private String trimAndValidate(String field, String fieldName) {
        if (field == null || field.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }
        return field.trim();
    }

}
