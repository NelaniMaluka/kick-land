package com.examplekicklaandwebsite.KickLaand.service.impl;

import com.examplekicklaandwebsite.KickLaand.config.JwtProvider;
import com.examplekicklaandwebsite.KickLaand.model.Newsletter;
import com.examplekicklaandwebsite.KickLaand.model.USER_ROLE;
import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.repository.NewsletterRepository;
import com.examplekicklaandwebsite.KickLaand.repository.UserAccountRepository;
import com.examplekicklaandwebsite.KickLaand.request.CreateAccountRequest;
import com.examplekicklaandwebsite.KickLaand.response.AuthResponse;
import com.examplekicklaandwebsite.KickLaand.response.ErrorResponse;
import com.examplekicklaandwebsite.KickLaand.service.AuthService;
import com.examplekicklaandwebsite.KickLaand.service.CustomUserDetailsService;
import com.examplekicklaandwebsite.KickLaand.util.FormValidation;
import org.springframework.dao.DataAccessException;
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


import java.util.Collection;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userAccountRepository;
    private final NewsletterRepository newsletterRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthServiceImpl(UserAccountRepository userAccountRepository, NewsletterRepository newsletterRepository, JwtProvider jwtProvider, CustomUserDetailsService customUserDetailsService) {
        this.userAccountRepository = userAccountRepository;
        this.newsletterRepository = newsletterRepository;
        this.jwtProvider = jwtProvider;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public ResponseEntity<?> login(UserAccount userAccount) {
        try {
            // Validate input
            if (!FormValidation.isValidEmail(userAccount.getEmail())) {
                return ResponseEntity.badRequest()
                        .body(new ErrorResponse("Invalid email format", "Please enter a valid email address."));
            }
            if (!FormValidation.isValidPassword(userAccount.getPassword())) {
                return ResponseEntity.badRequest()
                        .body(new ErrorResponse("Invalid password format", "Password must contain at least 8 characters, including a number, an uppercase and lowercase letter, and a special character."));
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
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(new ErrorResponse("Invalid password", "Password you entered is incorrect."));
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorResponse("User Not Found", "No user exists with the provided email address."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal Server Error","An error occurred during login, please try again later."));
        }
    }

    @Override
    public ResponseEntity<?> createAccount(CreateAccountRequest req) {
        try {
            UserAccount userAccount = req.userAccount();
            Boolean signUpForNewsletter = req.signUpForNewsletter();

            // Validate input
            if (!FormValidation.isValidEmail(userAccount.getEmail())) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Invalid email format", "Please provide a valid email address."));
            }
            if (!FormValidation.isValidPassword(userAccount.getPassword())) {
                return ResponseEntity.badRequest()
                        .body(new ErrorResponse("Invalid password format", "Password must contain at least 8 characters, including a number, an uppercase and lowercase letter, and a special character."));
            }

            // Check if user already exists by email
            if (userAccountRepository.findByEmail(userAccount.getEmail()) != null) {
                return ResponseEntity.badRequest()
                        .body(new ErrorResponse("User Found","User with this email already exists"));
            }

            // Handle Newsletter subscription
            if (signUpForNewsletter) {
                Newsletter existingNewsletter = newsletterRepository.findByEmail(userAccount.getEmail());
                if (existingNewsletter == null) {
                    // Create a new Newsletter entry if it doesn't exist
                    Newsletter newsletter = new Newsletter();
                    newsletter.setEmail(userAccount.getEmail());

                    newsletterRepository.save(newsletter);
                }
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

        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Database Error", "Failed to interact with the database."));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal Server Error","An error occurred during creating your account, please try again later."));
        }
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
