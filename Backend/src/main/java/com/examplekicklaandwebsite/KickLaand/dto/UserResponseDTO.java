package com.examplekicklaandwebsite.KickLaand.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDTO {
	private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String address;
    
    public UserResponseDTO(Integer id, String firstname, String lastname, String email, String phoneNumber, String address) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
}
