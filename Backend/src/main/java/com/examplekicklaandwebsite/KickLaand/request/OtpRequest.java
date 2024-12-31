package com.examplekicklaandwebsite.KickLaand.request;

import lombok.Builder;

@Builder
public record OtpRequest(Integer otp, String email) {

}
