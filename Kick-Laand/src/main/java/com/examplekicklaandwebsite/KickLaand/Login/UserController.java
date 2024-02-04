package com.examplekicklaandwebsite.KickLaand.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examplekicklaandwebsite.KickLaand.Newsletter.Newsletter;
import com.examplekicklaandwebsite.KickLaand.Newsletter.NewsletterRepository;

import java.util.Optional;

import javax.naming.Binding;
import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@Validated
public class UserController {
	
    private final UserAccountRepository userAccountRepository;
    private final NewsletterRepository newsletterRepository;
    private @Email(message = "Please provide a valid email") String newsletter;

    @Autowired
    public UserController(UserAccountRepository userAccountRepository, NewsletterRepository newsletterRepository) {
        this.userAccountRepository = userAccountRepository;
        this.newsletterRepository = newsletterRepository;
    }

    @PostMapping(path="/Backend/Login")
    public ResponseEntity<?> login(@Valid @RequestBody UserAccount userAccount) {
    	
    	try {
    		userAccountRepository.findByUsernameAndEmail(userAccount.username,userAccount.password);
    		return ResponseEntity.ok( true);
    	} catch (Exception e) {
    		return ResponseEntity.badRequest().body("Invalid Credentials");
    	}
    }
    
    @PostMapping(path="/Backend/Create-Account")
    public ResponseEntity<?> createAccount(@Valid @RequestBody UserAccount userAccount, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid Email Format");
        }

        try {
            userAccountRepository.save(userAccount);

            // Create a Newsletter instance and set the email
            Newsletter newsletter = new Newsletter();
            newsletter.setEmail(userAccount.getEmail());

            // Assuming FindByEmail returns a nullable result
            Optional<Newsletter> existingNewsletter = Optional.ofNullable(newsletterRepository.findByEmail(userAccount.getEmail()));
            
            if (!existingNewsletter.isPresent()) {
                newsletterRepository.save(newsletter);
            }

            return ResponseEntity.ok("Account created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create account");
        }
    }


}
