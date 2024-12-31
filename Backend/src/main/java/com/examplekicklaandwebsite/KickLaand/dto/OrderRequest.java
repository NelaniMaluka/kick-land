package com.examplekicklaandwebsite.KickLaand.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record OrderRequest(
        @Email(message = "Please provide a valid email address") String email,
        @NotNull String address,
        @NotNull String province,
        @NotNull String ZIPCode,
        @NotNull String firstname,
        @NotNull String lastname,
        @Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Please provide a valid phone number") String phoneNumber) {
}
