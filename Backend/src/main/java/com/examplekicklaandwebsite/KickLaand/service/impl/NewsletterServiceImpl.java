package com.examplekicklaandwebsite.KickLaand.service.impl;

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
    public String addNewsletter(Newsletter newsletter) {
        try {
            String email = (newsletter.getEmail() != null) ? newsletter.getEmail().trim() : null;

            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("Please Enter a Valid Email");
            }

            if (!FormValidation.isValidEmail(email)) {
                throw new IllegalArgumentException("Invalid Email Format");
            }

            if (newsletterRepository.findByEmail(email) != null) {
                throw new IllegalStateException("Email Already Subscribed");
            }

            newsletterRepository.save(newsletter);

            return "We Received Your Email";
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred");
        }
    }
}
