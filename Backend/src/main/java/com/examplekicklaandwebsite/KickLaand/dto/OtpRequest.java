package com.examplekicklaandwebsite.KickLaand.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
public record OtpRequest ( Integer otp, String email){

}
