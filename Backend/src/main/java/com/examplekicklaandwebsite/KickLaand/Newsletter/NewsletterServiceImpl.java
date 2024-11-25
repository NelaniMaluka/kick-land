package com.examplekicklaandwebsite.KickLaand.Newsletter;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.regex.Pattern;

@Service
public class NewsletterServiceImpl implements NewsletterService {

    private final NewsletterRepository newsletterRepository;

    public NewsletterServiceImpl(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
    );

    @Override
    @Transactional
    public String addNewsletter(Newsletter newsletter) {
        String email = (newsletter.getEmail() != null) ? newsletter.getEmail().trim() : null;

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Please Enter a Valid Email");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid Email Format");
        }

        if (newsletterRepository.findByEmail(email) != null) {
            throw new IllegalStateException("Email Already Subscribed");
        }

        newsletter.setEmail(email);
        newsletterRepository.save(newsletter);

        return "We Received Your Email";
    }
}
