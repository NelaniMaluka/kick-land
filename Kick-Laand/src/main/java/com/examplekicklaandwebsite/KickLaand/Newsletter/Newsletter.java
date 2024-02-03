package com.examplekicklaandwebsite.KickLaand.Newsletter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
public class Newsletter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	@Email(message = "Please provide a valid email")
	public String email;

	public void setEmail(String email) {
		this.email = email;
	}

	
}
