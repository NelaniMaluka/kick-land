package com.examplekicklaandwebsite.KickLaand.ContactUs;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examplekicklaandwebsite.KickLaand.Newsletter.Newsletter;
import com.examplekicklaandwebsite.KickLaand.Newsletter.NewsletterRepository;

@RestController
@Validated
public class ContactUsController {
	
	private final ContactUsRepository contactUsRepository;
	private final NewsletterRepository newsletterRepository;
	
	public ContactUsController(ContactUsRepository contactUsRepository, NewsletterRepository newsletterRepository) {
		super();
		this.contactUsRepository = contactUsRepository;
		this.newsletterRepository = newsletterRepository;
	}

	@PostMapping(path="/Backend/ContactUs")
	public ResponseEntity<?> sendInfo (@Valid @RequestBody ContactUs contactUs) {
		
		try {
			contactUsRepository.save(contactUs);

            // Create a Newsletter instance and set the email
            Newsletter newsletter = new Newsletter();
            newsletter.setEmail(contactUs.getEmail());

            // Assuming FindByEmail returns a nullable result
            Optional<Newsletter> existingNewsletter = Optional.ofNullable(newsletterRepository.findByEmail(contactUs.getEmail()));
            
            if (!existingNewsletter.isPresent()) {
                newsletterRepository.save(newsletter);
            }
            return ResponseEntity.ok("We Recieved your message");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Invalid Email");
		}
		
	}

	
}