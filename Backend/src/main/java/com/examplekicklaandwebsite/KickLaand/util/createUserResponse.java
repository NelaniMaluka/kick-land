package com.examplekicklaandwebsite.KickLaand.util;

import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.response.UserResponse;

public class createUserResponse {

    // Private constructor to prevent instantiation
    private createUserResponse() {
    }

    // Static factory method to create a UserResponseDto
    public static UserResponse createResponse(UserAccount user) {
        return new UserResponse(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPhonenumber() != null ? user.getPhonenumber() : "",
                user.getAddress() != null ? user.getAddress() : "");
    }
}
