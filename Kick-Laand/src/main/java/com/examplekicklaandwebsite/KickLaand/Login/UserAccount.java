package com.examplekicklaandwebsite.KickLaand.Login;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class UserAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	public String username;
	
	public String surname;
	
	@Valid
	@Email(message = "Please provide a valid email")
	public String email;
	
	@Valid
	@Size(min = 8, max=50, message = "Password must be at least 8 characters long")
	public String password;

	public String getEmail() {
		return email;
    }
	
	
	
}
