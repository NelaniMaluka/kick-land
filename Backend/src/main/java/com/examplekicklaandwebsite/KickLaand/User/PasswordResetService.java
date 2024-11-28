package com.examplekicklaandwebsite.KickLaand.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PasswordResetService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserAccountRepository userAccountRepository;

    //@Autowired  // Inject the PasswordEncoder bean
    //private PasswordEncoder passwordEncoder;
    
    public void createPasswordResetRequest(String email) {
        UserAccount user = userAccountRepository.findByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }

        sendPasswordResetEmail(user.getEmail());
    }

    public void resetPassword(String email, String newPassword) {
        UserAccount user = userAccountRepository.findByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }

        user.setPassword(newPassword);
        //user.setPassword(passwordEncoder.encode(newPassword));
        userAccountRepository.save(user);

        // Optionally, you can send an email notifying the user about the password change
        sendPasswordChangedEmail(user.getEmail());
    }

    private void sendPasswordResetEmail(String recipientEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Password Reset Request");
        message.setText("Your password has been reset. If you did not request this change, please contact support.");

        javaMailSender.send(message);
    }

    private void sendPasswordChangedEmail(String recipientEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Password Changed");
        message.setText("Your password has been successfully changed.");

        javaMailSender.send(message);
    }
}
