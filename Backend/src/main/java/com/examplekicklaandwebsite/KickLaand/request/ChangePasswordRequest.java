package com.examplekicklaandwebsite.KickLaand.request;

import jakarta.validation.constraints.NotNull;

public record ChangePasswordRequest(
        @NotNull(message = "Password cannot be null")String password,
        @NotNull(message = "Repeat Password Id cannot be null")String repeatPassword,
        @NotNull(message = "Email cannot be null")String email) {

}
