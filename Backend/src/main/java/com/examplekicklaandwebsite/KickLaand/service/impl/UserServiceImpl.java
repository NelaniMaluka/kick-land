package com.examplekicklaandwebsite.KickLaand.service.impl;

import java.util.Map;

import com.examplekicklaandwebsite.KickLaand.util.createUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.examplekicklaandwebsite.KickLaand.response.UserResponse;
import com.examplekicklaandwebsite.KickLaand.model.Newsletter;
import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.repository.NewsletterRepository;
import com.examplekicklaandwebsite.KickLaand.repository.UserAccountRepository;
import com.examplekicklaandwebsite.KickLaand.service.UserService;
import com.examplekicklaandwebsite.KickLaand.util.FormValidation;

@Service
public class UserServiceImpl implements UserService {

    private final UserAccountRepository userAccountRepository;
    private final NewsletterRepository newsletterRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserAccountRepository userAccountRepository,
            NewsletterRepository newsletterRepository) {
        this.userAccountRepository = userAccountRepository;
        this.newsletterRepository = newsletterRepository;
    }

    @Override
    public ResponseEntity<?> login(UserAccount userAccount) {
        try {
            // Validate input
            if (!FormValidation.isValidEmail(userAccount.getEmail())) {
                return ResponseEntity.badRequest().body("Invalid email format");
            }
            if (!FormValidation.isValidPassword(userAccount.getPassword())) {
                return ResponseEntity.badRequest().body("Invalid password format");
            }

            // Find user by email
            UserAccount user = userAccountRepository.findByEmail(userAccount.getEmail());

            if (user != null) {
                // Check if the provided password matches the stored encoded password
                if (passwordEncoder.matches(userAccount.getPassword(), user.getPassword())) {
                    UserResponse userResponseDTO = createUserResponse.createResponse(user);
                    return ResponseEntity.ok(userResponseDTO);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Not Found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }

    @Override
    public ResponseEntity<?> createAccount(UserAccount userAccount) {
        try {
            // Validate input
            if (!FormValidation.isValidEmail(userAccount.getEmail())) {
                return ResponseEntity.badRequest().body("Invalid email format");
            }
            if (!FormValidation.isValidPassword(userAccount.getPassword())) {
                return ResponseEntity.badRequest().body("Password must contain at least 8 characters, including a number, an uppercase and lowercase letter, and a special character");
            }

            // Check if user already exists by email
            if (userAccountRepository.findByEmail(userAccount.getEmail()) != null) {
                return ResponseEntity.badRequest().body("User with this email already exists");
            }

            // Handle Newsletter subscription
            Newsletter existingNewsletter = newsletterRepository.findByEmail(userAccount.getEmail());
            if (existingNewsletter == null) {
                // Create a new Newsletter entry if it doesn't exist
                Newsletter newsletter = Newsletter.builder()
                        .email(userAccount.getEmail())
                        .build();

                newsletterRepository.save(newsletter);
            }

            // Encode the password securely
            userAccount = userAccount.toBuilder()
                    .password(passwordEncoder.encode(userAccount.getPassword()))
                    .build();

            // Save new user account
            userAccountRepository.save(userAccount);

            // Create and return the response DTO
            UserResponse userResponse = createUserResponse.createResponse(userAccount);
            return ResponseEntity.ok(userResponse);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create account: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateUserFields(Integer userId, Map<String, String> updates) {
        try {
            // Validate updates
            if (updates.isEmpty()) {
                return ResponseEntity.badRequest().body("No fields provided to update");
            }

            UserAccount user = userAccountRepository.findById(userId).orElse(null);

            if (user != null) {
                for (Map.Entry<String, String> entry : updates.entrySet()) {
                    String field = entry.getKey();
                    String value = entry.getValue();

                    switch (field.toLowerCase()) {
                        case "firstname":
                            if (value == null || value.trim().isEmpty()) {
                                return ResponseEntity.badRequest().body("Firstname cannot be empty");
                            }
                            user = user.toBuilder().firstname(value).build();
                            break;
                        case "email":
                            if (!FormValidation.isValidEmail(value)) {
                                return ResponseEntity.badRequest().body("Invalid email format");
                            }
                            user = user.toBuilder().email(value).build();
                            break;
                        case "lastname":
                            if (value == null || value.trim().isEmpty()) {
                                return ResponseEntity.badRequest().body("Lastname cannot be empty");
                            }
                            user = user.toBuilder().lastname(value).build();
                            break;
                        case "phonenumber":
                            user = user.toBuilder().phonenumber(value).build();
                            break;
                        case "address":
                            user = user.toBuilder().address(value).build();
                            break;
                        default:
                            return ResponseEntity.badRequest().body("Invalid field specified");
                    }
                }

                userAccountRepository.save(user);

                // Return a full response with all the fields
                UserResponse userResponseDTO = new UserResponse(
                        user.getId(),
                        user.getFirstname(),
                        user.getLastname(),
                        user.getEmail(),
                        user.getPhonenumber(),
                        user.getAddress());
                return ResponseEntity.ok(userResponseDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update user fields");
        }
    }

}
