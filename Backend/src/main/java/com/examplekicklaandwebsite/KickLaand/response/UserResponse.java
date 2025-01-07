package com.examplekicklaandwebsite.KickLaand.response;

import lombok.Data;

@Data
public class UserResponse {
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;

    public UserResponse( String firstname, String lastname, String email, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}
