package com.examplekicklaandwebsite.KickLaand.service.impl;

import java.util.Collection;
import java.util.Map;

import com.examplekicklaandwebsite.KickLaand.config.JwtProvider;
import com.examplekicklaandwebsite.KickLaand.model.USER_ROLE;
import com.examplekicklaandwebsite.KickLaand.response.AuthResponse;
import com.examplekicklaandwebsite.KickLaand.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

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
                    String username = userAccount.getEmail();
                    String password = userAccount.getPassword();

                    Authentication authentication = authenticate(username,password);

                    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                    String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

                    String jwt= jwtProvider.generateToken(authentication);

                    AuthResponse authResponse = new AuthResponse();
                    authResponse.setJwt(jwt);
                    authResponse.setMessage("Register Success");
                    authResponse.setRole(USER_ROLE.valueOf(role));

                    return new ResponseEntity<>(authResponse, HttpStatus.OK);
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
                Newsletter newsletter = new Newsletter();
                newsletter.setEmail(userAccount.getEmail());

                newsletterRepository.save(newsletter);
            }

            // Encode the password securely
            userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));

            // Save new user account
            userAccountRepository.save(userAccount);

            Authentication authentication = new UsernamePasswordAuthenticationToken(userAccount.getEmail(), userAccount.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt= jwtProvider.generateToken(authentication);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setJwt(jwt);
            authResponse.setMessage("Register Success");
            authResponse.setRole(userAccount.getRole());

            return new ResponseEntity<>(authResponse, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create account: " + e.getMessage());
        }
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
        UserAccount user = findUserByEmail(email);
        return user;
    }

    @Override
    public UserAccount findUserByEmail(String email) throws Exception {
        UserAccount user = userAccountRepository.findByEmail(email);

        if (user == null) {
            throw new BadCredentialsException("User with email " + email + " does not exist");
        }
        return user;
    }


    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        if(userDetails==null){
            throw new BadCredentialsException("Invalid username...");
        }

        if (!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
    }
}
