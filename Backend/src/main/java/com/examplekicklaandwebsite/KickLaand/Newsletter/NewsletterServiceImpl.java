package com.examplekicklaandwebsite.KickLaand.Newsletter;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewsletterServiceImpl implements NewsletterService {

    private final NewsletterRepository newsletterRepository;

    public NewsletterServiceImpl(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }

    @Override
    @Transactional
    public String addNewsletter(Newsletter newsletter) {
        try {
            if (newsletter.getEmail() == null) {
                throw new IllegalArgumentException("Please Enter a Valid Email");
            }

            String trimmedEmail = newsletter.getEmail().trim();
            Newsletter existingNewsletter = newsletterRepository.findByEmail(trimmedEmail);

            if (existingNewsletter != null) {
                throw new IllegalStateException("Email Already Subscribed");
            }

            newsletter.setEmail(trimmedEmail);
            newsletterRepository.save(newsletter);

            return "We Received Your Email";
        } catch (IllegalArgumentException | IllegalStateException e) {
            throw e; // Re-throw the specific exceptions
        } catch (Exception e) {
            throw new RuntimeException("An error occurred"); // Convert other exceptions to a runtime exception
        }
    }

}

