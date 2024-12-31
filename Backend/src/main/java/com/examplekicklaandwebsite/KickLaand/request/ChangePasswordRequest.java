package com.examplekicklaandwebsite.KickLaand.request;

public record ChangePasswordRequest(String password, String repeatPassword, String email) {

}
