package com.examplekicklaandwebsite.KickLaand.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailBody {

    @NotNull(message = "Recipient Email Address cannot be null")
    private String to;

    @NotNull(message = "Email Subject cannot be null")
    private String subject;

    @NotNull(message = "Email Text cannot be null")
    private String text;
}

