package com.examplekicklaandwebsite.KickLaand.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record MailBody(
        @NotNull(message = "Recipient Email Address cannot be null")String to,
        @NotNull(message = "Email Subject cannot be null")String subject,
        @NotNull(message = "Email Text cannot be null")String text) {
	
}
