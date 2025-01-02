package com.examplekicklaandwebsite.KickLaand.service.impl;

import java.util.Map;

import com.examplekicklaandwebsite.KickLaand.config.JwtProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.examplekicklaandwebsite.KickLaand.response.UserResponse;
import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.repository.UserAccountRepository;
import com.examplekicklaandwebsite.KickLaand.service.UserService;
import com.examplekicklaandwebsite.KickLaand.util.FormValidation;


@Service
public class UserServiceImpl implements UserService {

    private final UserAccountRepository userAccountRepository;
    private final JwtProvider jwtProvider;

    public UserServiceImpl(UserAccountRepository userAccountRepository, JwtProvider jwtProvider) {
        this.userAccountRepository = userAccountRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public ResponseEntity<?> updateUserFields(UserAccount user, Map<String, String> updates) {
        try {
            // Validate updates
            if (updates.isEmpty()) {
                return ResponseEntity.badRequest().body("No fields provided to update");
            }

            if (user != null) {
                for (Map.Entry<String, String> entry : updates.entrySet()) {
                    String field = entry.getKey();
                    String value = entry.getValue();

                    switch (field.toLowerCase()) {
                        case "firstname":
                            if (value == null || value.trim().isEmpty()) {
                                return ResponseEntity.badRequest().body("Firstname cannot be empty");
                            }
                            user.setFirstname(value);
                            break;
                        case "email":
                            if (!FormValidation.isValidEmail(value)) {
                                return ResponseEntity.badRequest().body("Invalid email format");
                            }
                            user.setEmail(value);
                            break;
                        case "lastname":
                            if (value == null || value.trim().isEmpty()) {
                                return ResponseEntity.badRequest().body("Lastname cannot be empty");
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

    @Override
    public UserAccount findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        UserAccount user = userAccountRepository.findByEmail(email);
        if (user == null) {
            throw new BadCredentialsException("User with email " + email + " does not exist");
        }
        return user;
    }

}
