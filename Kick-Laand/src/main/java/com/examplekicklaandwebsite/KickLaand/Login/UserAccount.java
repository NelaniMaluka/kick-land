package com.examplekicklaandwebsite.KickLaand.Login;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.examplekicklaandwebsite.KickLaand.Newsletter.Newsletter;

 

@Entity
public class UserAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	@NotBlank(message= "Please provide username")
	public String username;
	
	@NotBlank(message= "Please provide Password")
	public String surname;
	
	@Email(message = "Please provide a valid email")
	public String email;
	
	@Size(min = 8, max=50, message = "Password must be at least 8 characters long")
	public String password;

	public String getEmail() {
		return email;
    }
	
	
	
}
