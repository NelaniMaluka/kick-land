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
        if (contactUs.getEmail() == null || contactUs.getPhoneNumber() == null || contactUs.getMessage() == null) {
            throw new Exception("Email, Phone Number, and Message are required.");
        }

        Newsletter existingNewsletter = newsletterRepository.findByEmail(contactUs.getEmail());
        if (existingNewsletter == null) {
            Newsletter newsletter = new Newsletter();
            newsletter.setEmail(contactUs.getEmail());
            newsletterRepository.save(newsletter);
        }

        contactUsRepository.save(contactUs);
        return "We Received your message";
    }
}

