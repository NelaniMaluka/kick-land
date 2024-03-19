package com.examplekicklaandwebsite.KickLaand.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examplekicklaandwebsite.KickLaand.Newsletter.Newsletter;
import com.examplekicklaandwebsite.KickLaand.Newsletter.NewsletterRepository;

import java.util.Map;
import java.util.Optional;

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

    @PostMapping(path = "/api/user/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserAccount userAccount) {
        try {
            UserAccount user = userAccountRepository.findByEmailAndPassword(userAccount.getEmail(), userAccount.getPassword());

            if (user != null) {
            	UserResponseDTO userResponseDTO = createUserResponseDTO(user);
                return ResponseEntity.ok(userResponseDTO);

            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }


    @PostMapping(path = "/api/user/create-account")
    public ResponseEntity<?> createAccount(@Valid @RequestBody UserAccount userAccount ) {
     
        try {
        	// Check if user already exists
            if (userAccountRepository.findByEmail(userAccount.getEmail()) != null) {
                return ResponseEntity.badRequest().body("User with this email already exists");
            }
        	
            // Create a Newsletter instance and set the email
            Newsletter newsletter = new Newsletter();
            newsletter.setEmail(userAccount.getEmail());

            // Assuming FindByEmail returns a nullable result
            Optional<Newsletter> existingNewsletter = Optional
                    .ofNullable(newsletterRepository.findByEmail(userAccount.getEmail()));

            if (!existingNewsletter.isPresent()) {
                newsletterRepository.save(newsletter);
            }

            // return ResponseEntity.ok("Account created successfully");
            if (userAccountRepository.findByEmail(userAccount.getEmail()) == null) {
        		userAccountRepository.save(userAccount);
        		UserResponseDTO userResponseDTO = createUserResponseDTO(userAccount);
                return ResponseEntity.ok(userResponseDTO);
        	} else {
        		return ResponseEntity.badRequest().body("User with this email already exists");
        	}

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create account");
        }
    }
    
    @PutMapping("/api/user/update-user/{userId}")
    public ResponseEntity<?> updateUserFields(@PathVariable @NonNull Integer userId, @Valid @RequestBody Map<String, String> updates) {
        try {
            UserAccount user = userAccountRepository.findById(userId)
                    .orElse(null);

            if (user != null) {
                // Update user fields
                for (Map.Entry<String, String> entry : updates.entrySet()) {
                    String field = entry.getKey();
                    String value = entry.getValue();

                    switch (field.toLowerCase()) {
                        case "username":
                            user.setUsername(value);
                            break;
                        case "email":
                            user.setEmail(value);
                            break;
                        case "surname":
                            user.setSurname(value);
                            break;
                        case "phonenumber":
                            user.setPhonenumber(value);
                            break;
                        case "address":
                            user.setAddress(value);
                            break;
                        default:
                            return ResponseEntity.badRequest().body("Invalid field specified");
                    }
                }

                userAccountRepository.save(user);
                UserResponseDTO userResponseDTO = createUserResponseDTO(user);
                return ResponseEntity.ok(userResponseDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update user fields");
        }
    }

    
    private UserResponseDTO createUserResponseDTO(UserAccount user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getSurname(),
                user.getPhonenumber() != null ? user.getPhonenumber() : "",
                user.getAddress() != null ? user.getAddress() : ""
        );
    }
    
//    public BCryptPasswordEncoder passwordEncoder (string password) {
//    	return new BCryptPasswordEncoder(password);
//    }
//    

}
