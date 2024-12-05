package com.examplekicklaandwebsite.KickLaand.dto;

import lombok.Builder;

@Builder
public record OtpRequest(Integer otp, String email) {

}
