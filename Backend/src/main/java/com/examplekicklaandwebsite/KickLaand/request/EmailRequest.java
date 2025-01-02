package com.examplekicklaandwebsite.KickLaand.request;

import jakarta.validation.constraints.Email;

public record EmailRequest(
        @Email(message = "Please provide a valid email address")String email) {
}
