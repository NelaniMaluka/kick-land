package com.examplekicklaandwebsite.KickLaand.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record OrderRequest(
        @Email(message = "Please provide a valid email address") String email,
        @NotNull(message = "Address cannot be null") String address,
        @NotNull(message = "Province cannot be null") String province,
        @NotNull(message = "ZIP Code cannot be null") String ZIPCode,
        @NotNull(message = "Firstname cannot be null") String firstname,
        @NotNull(message = "Lastname cannot be null") String lastname,
        @Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Please provide a valid phone number") String phoneNumber) {
}
