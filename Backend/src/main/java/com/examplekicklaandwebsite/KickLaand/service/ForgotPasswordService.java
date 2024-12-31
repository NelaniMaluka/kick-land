package com.examplekicklaandwebsite.KickLaand.service;

import com.examplekicklaandwebsite.KickLaand.request.ChangePasswordRequest;
import com.examplekicklaandwebsite.KickLaand.request.OtpRequest;
import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import org.springframework.http.ResponseEntity;

public interface ForgotPasswordService {
    ResponseEntity<?> verifyEmail(UserAccount userAccount);

    ResponseEntity<?> verifyOtp(OtpRequest otpRequest);

    ResponseEntity<?> changePassword(ChangePasswordRequest changePassword);
}
