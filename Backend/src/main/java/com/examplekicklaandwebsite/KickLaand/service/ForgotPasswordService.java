package com.examplekicklaandwebsite.KickLaand.service;

import com.examplekicklaandwebsite.KickLaand.request.ChangePasswordRequest;
import com.examplekicklaandwebsite.KickLaand.request.EmailRequest;
import com.examplekicklaandwebsite.KickLaand.request.OtpRequest;
import org.springframework.http.ResponseEntity;

public interface ForgotPasswordService {
    ResponseEntity<?> verifyEmail(EmailRequest req);

    ResponseEntity<?> verifyOtp(OtpRequest otpRequest);

    ResponseEntity<?> changePassword(ChangePasswordRequest changePassword);
}
