package com.examplekicklaandwebsite.KickLaand.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;

@Builder
public record OtpRequest(
        @NotNull(message = "OTP cannot be null")
        @Positive(message = "OTP must be a positive number")
        Integer otp,

        @NotNull(message = "Email cannot be null")
        @Email(message = "Please enter a valid email address")
        String email
) {
}

