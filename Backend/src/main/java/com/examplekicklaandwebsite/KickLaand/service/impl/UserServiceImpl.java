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
import com.examplekicklaandwebsite.KickLaand.response.ErrorResponse;
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
            if (req == null) {
                ErrorResponse errorResponse = new ErrorResponse("Bad Request", "No fields provided to update.");
                return ResponseEntity.badRequest().body(errorResponse);
            }

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            // Update fields from request
            if (req.firstname() != null && !req.firstname().trim().isEmpty()) {
                user.setFirstname(req.firstname());
            } else if (req.firstname() != null) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Bad Request", "Firstname cannot be empty."));
            }

            if (req.lastname() != null && !req.lastname().trim().isEmpty()) {
                user.setLastname(req.lastname());
            } else if (req.lastname() != null) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Bad Request", "Lastname cannot be empty."));
            }

            if (req.email() != null && FormValidation.isValidEmail(req.email())) {
                user.setEmail(req.email());
            } else if (req.email() != null) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Bad Request", "Invalid email format."));
            }

            if (req.phonenumber() != null) {
                user.setPhonenumber(req.phonenumber());
            }

            userAccountRepository.save(user);

            // Return updated user response
            UserResponse userResponseDTO = new UserResponse(
                    user.getFirstname(),
                    user.getLastname(),
                    user.getEmail(),
                    user.getPhonenumber()
            );
            return ResponseEntity.ok(userResponseDTO);

        } catch (Exception e) {
            // General error handling
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error", "Failed to update user fields: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @Override
    public UserAccount findUserByJwtToken(String jwt) throws Exception {
        try {
            String email = jwtProvider.getEmailFromJwtToken(jwt);
            UserAccount user = userAccountRepository.findByEmail(email);

            if (user == null) {
                throw new BadCredentialsException("User with email " + email + " does not exist");
            }

            return user;
        } catch (BadCredentialsException e) {
            ErrorResponse errorResponse = new ErrorResponse("Bad Credentials", e.getMessage());
            throw new BadCredentialsException(errorResponse.getMessage());
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error", "An error occurred while processing the token.");
            throw new Exception(errorResponse.getMessage(), e);
        }
    }
}
