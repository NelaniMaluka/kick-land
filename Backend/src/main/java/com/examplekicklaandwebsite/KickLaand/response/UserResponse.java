package com.examplekicklaandwebsite.KickLaand.response;

import lombok.Data;

@Data
public class UserResponse {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String address;

    public UserResponse(Integer id, String firstname, String lastname, String email, String phoneNumber,
            String address) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

}
