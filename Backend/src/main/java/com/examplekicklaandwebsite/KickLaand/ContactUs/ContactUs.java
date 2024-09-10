package com.examplekicklaandwebsite.KickLaand.ContactUs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.PersistenceConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactUs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	public String name;
	
	@Email(message = "Enter a valid email")
	@NotNull(message = "Enter a valid email")
	public String email;

	@Pattern(regexp = "(\\+27|0)[0-9]{9}", message = "Enter a valid phone number")
	public String phoneNumber;

	@NotNull(message = "Please Enter a message")
	public String message;

	@PersistenceConstructor
	public ContactUs( String name,
			@Email(message = "Enter a valid email") @NotNull(message = "Enter a valid email") String email,
			@Pattern(regexp = "(\\+27|0)[0-9]{9}", message = "Enter a valid phone number") String phoneNumber,
			@NotNull(message = "Please Enter a message") String message) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
