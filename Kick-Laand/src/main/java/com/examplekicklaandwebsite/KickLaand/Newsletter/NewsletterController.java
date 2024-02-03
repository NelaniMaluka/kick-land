package com.examplekicklaandwebsite.KickLaand.Newsletter;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsletterController {

	private final NewsletterRepository newsletterRepository;
	
	public NewsletterController(NewsletterRepository newsletterRepository) {
		super();
		this.newsletterRepository = newsletterRepository;
	}
	
	@PostMapping(path="/Backend/Newsletter")
	public ResponseEntity<?> addNewsletter(@Valid @RequestBody Newsletter newsletter) {
		try {
			newsletterRepository.save(newsletter);
			return ResponseEntity.ok("We Recieved Your Email");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Invalid Email");
		}
	}


	
}
