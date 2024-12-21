package com.examplekicklaandwebsite.KickLaand.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record OrderRequest(
        @NotNull Integer userId,
        @Email(message = "Please provide a valid email address") String userEmail,
        @NotNull String userAddress,
        @Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Please provide a valid phone number") String userPhoneNumber) {
}
