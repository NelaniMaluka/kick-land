package com.examplekicklaandwebsite.KickLaand.service.impl;

import com.examplekicklaandwebsite.KickLaand.config.JwtProvider;
import com.examplekicklaandwebsite.KickLaand.request.UserRequest;
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
    public ResponseEntity<?> updateUserFields(UserAccount user, UserRequest req) {
        try {
            // Validate the UserRequest
            if (req == null) {
                return ResponseEntity.badRequest().body("No fields provided to update.");
            }

            // Check if the user exists
            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            // Update the fields from the request
            if (req.firstname() != null && !req.firstname().trim().isEmpty()) {
                user.setFirstname(req.firstname());
            } else if (req.firstname() != null) {
                return ResponseEntity.badRequest().body("Firstname cannot be empty.");
            }

            if (req.lastname() != null && !req.lastname().trim().isEmpty()) {
                user.setLastname(req.lastname());
            } else if (req.lastname() != null) {
                return ResponseEntity.badRequest().body("Lastname cannot be empty.");
            }

            if (req.email() != null && FormValidation.isValidEmail(req.email())) {
                user.setEmail(req.email());
            } else if (req.email() != null) {
                return ResponseEntity.badRequest().body("Invalid email format.");
            }

            if (req.phonenumber() != null) {
                user.setPhonenumber(req.phonenumber());
            }

            // Save the updated user
            userAccountRepository.save(user);

            // Prepare response
            UserResponse userResponseDTO = new UserResponse(
                    user.getFirstname(),
                    user.getLastname(),
                    user.getEmail(),
                    user.getPhonenumber()
            );

            return ResponseEntity.ok(userResponseDTO);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update user fields: " + e.getMessage());
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
