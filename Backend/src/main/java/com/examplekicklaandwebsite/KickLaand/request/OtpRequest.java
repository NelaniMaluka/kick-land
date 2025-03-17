package com.examplekicklaandwebsite.KickLaand.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OtpRequest(
                @NotNull(message = "OTP cannot be null") Integer otp,
                @NotNull(message = "Email cannot be null") String email) {
}
