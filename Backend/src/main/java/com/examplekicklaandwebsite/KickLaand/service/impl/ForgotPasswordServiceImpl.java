package com.examplekicklaandwebsite.KickLaand.service.impl;

import com.examplekicklaandwebsite.KickLaand.dto.MailBody;
import com.examplekicklaandwebsite.KickLaand.request.ChangePasswordRequest;
import com.examplekicklaandwebsite.KickLaand.request.EmailRequest;
import com.examplekicklaandwebsite.KickLaand.request.OtpRequest;
import com.examplekicklaandwebsite.KickLaand.model.ForgotPassword;
import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.repository.ForgotPasswordRepository;
import com.examplekicklaandwebsite.KickLaand.repository.UserAccountRepository;
import com.examplekicklaandwebsite.KickLaand.response.ErrorResponse;
import com.examplekicklaandwebsite.KickLaand.service.EmailService;
import com.examplekicklaandwebsite.KickLaand.service.ForgotPasswordService;
import com.examplekicklaandwebsite.KickLaand.util.OtpGenerator;
import jakarta.mail.MessagingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Service
@Transactional
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private final UserAccountRepository userRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ForgotPasswordServiceImpl(UserAccountRepository userRepository, EmailService emailService,
            ForgotPasswordRepository forgotPasswordRepository) {
        super();
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.forgotPasswordRepository = forgotPasswordRepository;
    }

    @Override
    public ResponseEntity<?> verifyEmail(EmailRequest req) {
        try {
            UserAccount user = userRepository.findByEmail(req.email());
            if (user == null) {
                return ResponseEntity.badRequest()
                        .body(new ErrorResponse("Email Not Found", "No account exists with this email address. Please check the email address and try again."));
            }

            int otp = OtpGenerator.generatorOtp();

            MailBody mailBody = MailBody.builder()
                    .to(req.email())
                    .text("This is the otp for your forgot password request: " + otp)
                    .subject("OTP for forgot Password request")
                    .build();

            ForgotPassword fp = new ForgotPassword();
            fp.setOtp(otp);
            fp.setExpirationTime(new Date(System.currentTimeMillis() + 5 * 60 * 1000));
            fp.setUser(user);

            emailService.sendSimpleMessage(mailBody);
            forgotPasswordRepository.save(fp);

            return ResponseEntity.ok("Email sent for verification");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse("Data Integrity Violation", "Unable to process the request due to a conflict in the data. Please try again later."));
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Email Sending Failed", "There was an issue sending the email. Please try again later."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal Server Error", "An unexpected error occurred while processing your request. Please try again later."));
        }
    }

    @Override
    public ResponseEntity<?> verifyOtp(OtpRequest otpRequest) {
        try {
            // Verify if user exists
            UserAccount user = userRepository.findByEmail(otpRequest.email());
            if (user == null) {
                return ResponseEntity.badRequest()
                        .body(new ErrorResponse("Email Not Found", "No account exists with this email address. Please check the email address and try again."));
            }

            // Fetch OTP record for the user
            ForgotPassword fp = forgotPasswordRepository
                    .findByOtpandUser(otpRequest.otp(), user)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid OTP for email: " + otpRequest.email())); // Throw IllegalArgumentException here

            // Check expiration
            if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
                forgotPasswordRepository.deleteById(fp.getFpid());
                return ResponseEntity.status(HttpStatus.FORBIDDEN) // 403 for expired OTP
                        .body(new ErrorResponse("OTP Expired", "The OTP has expired. Please request a new one."));
            }

            return ResponseEntity.ok("OTP Verified");

        } catch (IllegalArgumentException e) { // Catch IllegalArgumentException here
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Invalid OTP", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal Server Error", "An unexpected error occurred while processing your OTP. Please try again later."));
        }
    }

    @Override
    public ResponseEntity<?> changePassword(ChangePasswordRequest changePassword) {
        try {
            // Check if passwords match
            if (!Objects.equals(changePassword.password(), changePassword.repeatPassword())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("Password Mismatch", "Please ensure the password and confirmation match."));
            }

            // Check if user exists
            UserAccount user = userRepository.findByEmail(changePassword.email());
            if (user == null) {
                return ResponseEntity.badRequest()
                        .body(new ErrorResponse("Email Not Found", "No account exists with this email address. Please check the email address and try again."));
            }

            // Encode the password and update
            String newPassword = passwordEncoder.encode(changePassword.password());
            user.setPassword(newPassword);

            // Save the updated user
            userRepository.save(user);

            // Delete the corresponding forgotPassword entry, if any
            forgotPasswordRepository.deleteByUser(user);

            return ResponseEntity.ok("Password successfully updated.");

        } catch (DataIntegrityViolationException e) {
            // Specific handling for database issues (if needed)
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse("Data Integrity Violation", "An error occurred due to a conflict in the data."));
        } catch (Exception e) {
            // Log unexpected exceptions for debugging purposes
            // Logger can be used here to log `e`
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal Server Error", "An unexpected error occurred while processing your new password. Please try again later."));
        }
    }

}
