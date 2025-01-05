package com.examplekicklaandwebsite.KickLaand.response;

import lombok.Data;

@Data
public class UserResponse {
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String address;

    public UserResponse( String firstname, String lastname, String email, String phoneNumber,
            String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

}
