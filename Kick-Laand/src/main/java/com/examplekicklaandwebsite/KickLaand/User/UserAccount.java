package com.examplekicklaandwebsite.KickLaand.User;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	public String username;

	public String surname;

	@Valid
	@Email(message = "Please provide a valid email")
	public String email;

	@Valid
	@Size(min = 8, max = 50, message = "Password must be at least 8 characters long")
	public String password;

	@OneToMany(mappedBy = "user")
	public List<Cart> cart;

	public UserAccount() {
		// You can initialize any default values here if needed
	}

	public String getEmail() {
		return email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Cart> getFavourites() {
		return cart;
	}

	public void setFavourites(List<Cart> cart) {
		this.cart = cart;
	}

}
