package com.examplekicklaandwebsite.KickLaand.ContactUs;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examplekicklaandwebsite.KickLaand.Newsletter.Newsletter;
import com.examplekicklaandwebsite.KickLaand.Newsletter.NewsletterRepository;

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
    public String sendInfo(ContactUs contactUs) throws Exception {
        try {
            if (contactUs.getName() == null || contactUs.getEmail() == null ||
                contactUs.getPhoneNumber() == null || contactUs.getMessage() == null) {
                throw new Exception("Email, Phone Number, and Message are required.");
            }

            // Check if the email exists in the newsletter repository
            Newsletter existingNewsletter = newsletterRepository.findByEmail(contactUs.getEmail());
            if (existingNewsletter == null) {
                // If not, create a new newsletter entry
                Newsletter newsletter = new Newsletter();
                newsletter.setEmail(contactUs.getEmail());
                newsletterRepository.save(newsletter);
            }

            // Save the contact message
            contactUsRepository.save(contactUs);

            return "We received your message";  // Return success message

        } catch (Exception e) {
            // Log the error and return a meaningful error message
            System.err.println("Error: " + e.getMessage());
            return "An error occurred while processing your message. Please try again later.";
        }
    }
}
