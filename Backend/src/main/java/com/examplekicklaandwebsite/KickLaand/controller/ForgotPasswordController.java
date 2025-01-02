package com.examplekicklaandwebsite.KickLaand.controller;

import com.examplekicklaandwebsite.KickLaand.request.ChangePasswordRequest;
import com.examplekicklaandwebsite.KickLaand.request.EmailRequest;
import com.examplekicklaandwebsite.KickLaand.request.OtpRequest;
import com.examplekicklaandwebsite.KickLaand.service.ForgotPasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/forgot-password")
public class ForgotPasswordController {
	
	private final ForgotPasswordService forgotPasswordService;

	public ForgotPasswordController(ForgotPasswordService forgotPasswordService) {
		this.forgotPasswordService = forgotPasswordService;
	}

	@PostMapping("/verify-mail")
	public ResponseEntity<?> verifyEmail(@Valid @RequestBody EmailRequest req){
		return forgotPasswordService.verifyEmail(req);

	}

	@PostMapping("/verify-otp")
	public ResponseEntity<?> verifyOtp(@Valid @RequestBody OtpRequest otpRequest){
		return forgotPasswordService.verifyOtp(otpRequest);
	}

	@PostMapping("/change-password")
	public ResponseEntity<?> changePasswordHandler(@Valid @RequestBody ChangePasswordRequest changePassword){
		return forgotPasswordService.changePassword(changePassword);
	}

}
