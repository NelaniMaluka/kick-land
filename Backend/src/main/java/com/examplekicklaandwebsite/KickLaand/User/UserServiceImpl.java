package com.examplekicklaandwebsite.KickLaand.User;

import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.examplekicklaandwebsite.KickLaand.Newsletter.Newsletter;
import com.examplekicklaandwebsite.KickLaand.Newsletter.NewsletterRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserAccountRepository userAccountRepository;
    private final NewsletterRepository newsletterRepository;
    private final PasswordResetService passwordResetService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserAccountRepository userAccountRepository, 
                          NewsletterRepository newsletterRepository,
                          PasswordResetService passwordResetService) {
        this.userAccountRepository = userAccountRepository;
        this.newsletterRepository = newsletterRepository;
        this.passwordResetService = passwordResetService;
    }

    @Override
    public ResponseEntity<?> login(UserAccount userAccount) {
        try {
            // Validate input
            if (!isValidEmail(userAccount.getEmail())) {
                return ResponseEntity.badRequest().body("Invalid email format");
            }
            if (!isValidPassword(userAccount.getPassword())) {
                return ResponseEntity.badRequest().body("Invalid password format");
            }

            // Find user by email
            UserAccount user = userAccountRepository.findByEmail(userAccount.getEmail());

            if (user != null) {
                // Check if the provided password matches the stored encoded password
                if (passwordEncoder.matches(userAccount.getPassword(), user.getPassword())) {
                    UserResponseDTO userResponseDTO = createUserResponseDTO(user);
                    return ResponseEntity.ok(userResponseDTO);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }

    @Override
    public ResponseEntity<?> createAccount(UserAccount userAccount) {
        try {
            // Validate input
            if (!isValidEmail(userAccount.getEmail())) {
                return ResponseEntity.badRequest().body("Invalid email format");
            }
            if (!isValidPassword(userAccount.getPassword())) {
                return ResponseEntity.badRequest().body("Password must be at least 8 characters long");
            }

            if (userAccountRepository.findByEmail(userAccount.getEmail()) != null) {
                return ResponseEntity.badRequest().body("User with this email already exists");
            }

            Newsletter newsletter = new Newsletter();
            newsletter.setEmail(userAccount.getEmail());

            Optional<Newsletter> existingNewsletter = Optional.ofNullable(newsletterRepository.findByEmail(userAccount.getEmail()));

            if (!existingNewsletter.isPresent()) {
                newsletterRepository.save(newsletter);
            }

            if (userAccountRepository.findByEmail(userAccount.getEmail()) == null) {
                userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
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
                        case "username":
                            if (value == null || value.trim().isEmpty()) {
                                return ResponseEntity.badRequest().body("Username cannot be empty");
                            }
                            user.setFirstname(value);
                            break;
                        case "email":
                            if (!isValidEmail(value)) {
                                return ResponseEntity.badRequest().body("Invalid email format");
                            }
                            user.setEmail(value);
                            break;
                        case "surname":
                            if (value == null || value.trim().isEmpty()) {
                                return ResponseEntity.badRequest().body("Surname cannot be empty");
                            }
                            user.setLastname(value);
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

                // Return a full response with all the fields
                UserResponseDTO userResponseDTO = new UserResponseDTO(
                    user.getId(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getEmail(),
                    user.getPhonenumber(),
                    user.getAddress()
                );
                return ResponseEntity.ok(userResponseDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update user fields");
        }
    }


    @Override
    public ResponseEntity<String> forgotPassword(UserAccount userAccount) {
        try {
            if (!isValidEmail(userAccount.getEmail())) {
                return ResponseEntity.badRequest().body("Invalid email format");
            }
            passwordResetService.createPasswordResetRequest(userAccount.getEmail());
            return ResponseEntity.ok("Password reset email sent successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    private UserResponseDTO createUserResponseDTO(UserAccount user) {
        return new UserResponseDTO(
                user.getId(),
                user.getFirstname(),
                user.getEmail(),
                user.getLastname(),
                user.getPhonenumber() != null ? user.getPhonenumber() : "",
                user.getAddress() != null ? user.getAddress() : ""
        );
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }
}
