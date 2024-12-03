package com.examplekicklaandwebsite.KickLaand.util;

import com.examplekicklaandwebsite.KickLaand.dto.UserResponseDTO;
import com.examplekicklaandwebsite.KickLaand.model.UserAccount;

public class UserResponse {
	
	private UserResponse () {}
	
    public static UserResponseDTO createUserResponseDTO(UserAccount user) {
        return new UserResponseDTO(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPhonenumber() != null ? user.getPhonenumber() : "",
                user.getAddress() != null ? user.getAddress() : ""
        );
    }

}
