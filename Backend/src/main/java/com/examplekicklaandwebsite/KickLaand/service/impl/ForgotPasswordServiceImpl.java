package com.examplekicklaandwebsite.KickLaand.service.impl;

import com.examplekicklaandwebsite.KickLaand.dto.MailBody;
import com.examplekicklaandwebsite.KickLaand.request.ChangePasswordRequest;
import com.examplekicklaandwebsite.KickLaand.request.OtpRequest;
import com.examplekicklaandwebsite.KickLaand.model.ForgotPassword;
import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.repository.ForgotPasswordRepository;
import com.examplekicklaandwebsite.KickLaand.repository.UserAccountRepository;
import com.examplekicklaandwebsite.KickLaand.service.EmailService;
import com.examplekicklaandwebsite.KickLaand.service.ForgotPasswordService;
import com.examplekicklaandwebsite.KickLaand.util.OtpGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public ResponseEntity<?> verifyEmail(UserAccount userAccount) {
        try {
            UserAccount user = userRepository.findByEmail(userAccount.getEmail());
            if (user == null) {
                throw new UsernameNotFoundException("Please provide a valid email!");
            }

            int otp = OtpGenerator.generatorOtp();

            MailBody mailBody = MailBody.builder()
                    .to(userAccount.getEmail())
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
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @Override
    public ResponseEntity<?> verifyOtp(OtpRequest otpRequest) {
        try {
            UserAccount user = userRepository.findByEmail(otpRequest.email());
            if (user == null) {
                throw new UsernameNotFoundException("Please provide a valid email!");
            }

            ForgotPassword fp = forgotPasswordRepository
                    .findByOtpandUser(otpRequest.otp(), user)
                    .orElseThrow(() -> new RuntimeException("Invalid OTP for email: " + otpRequest.email()));

            if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
                forgotPasswordRepository.deleteById(fp.getFpid());
                return new ResponseEntity<>("Otp has expired", HttpStatus.EXPECTATION_FAILED);
            }

            return ResponseEntity.ok("OTP Verified");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @Override
    public ResponseEntity<?> changePassword(ChangePasswordRequest changePassword) {
        try {
            if (!Objects.equals(changePassword.password(), changePassword.repeatPassword())) {
                return new ResponseEntity<>("Please enter the password again!", HttpStatus.EXPECTATION_FAILED);
            }

            UserAccount user = userRepository.findByEmail(changePassword.email());
            if (user == null) {
                throw new UsernameNotFoundException("User not found!");
            }

            String newPassword = passwordEncoder.encode(changePassword.password());

            user.setPassword(newPassword);

            userRepository.save(user);

            forgotPasswordRepository.deleteByUser(user);

            return ResponseEntity.ok("Password Successfully updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

}
