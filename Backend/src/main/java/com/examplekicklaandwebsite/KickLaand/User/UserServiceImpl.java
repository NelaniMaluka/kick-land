package com.examplekicklaandwebsite.KickLaand.User;

import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.examplekicklaandwebsite.KickLaand.Newsletter.Newsletter;
import com.examplekicklaandwebsite.KickLaand.Newsletter.NewsletterRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserAccountRepository userAccountRepository;
    private final NewsletterRepository newsletterRepository;
    private final PasswordResetService passwordResetService;

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

    @Override
    public ResponseEntity<?> createAccount(UserAccount userAccount) {
        try {
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
            UserAccount user = userAccountRepository.findById(userId).orElse(null);

            if (user != null) {
                for (Map.Entry<String, String> entry : updates.entrySet()) {
                    String field = entry.getKey();
                    String value = entry.getValue();

                    switch (field.toLowerCase()) {
                        case "username":
                            user.setFirstname(value);
                            break;
                        case "email":
                            user.setEmail(value);
                            break;
                        case "surname":
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
                UserResponseDTO userResponseDTO = createUserResponseDTO(user);
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
            passwordResetService.createPasswordResetRequest(userAccount.getEmail());
            return ResponseEntity.ok("Password reset email sent successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @Override
    public ResponseEntity<String> resetPassword(Map<String, String> requestBody) {
        try {
            String email = requestBody.get("email");
            String newPassword = requestBody.get("newPassword");
            passwordResetService.resetPassword(email, newPassword);
            return ResponseEntity.ok("Password reset successfully");
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
}

