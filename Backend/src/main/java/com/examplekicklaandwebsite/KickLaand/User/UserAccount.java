package com.examplekicklaandwebsite.KickLaand.User;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.examplekicklaandwebsite.KickLaand.Orders.UserOrders;
import com.examplekicklaandwebsite.KickLaand.UserCart.UserCarts;

@Entity
@Table(name = "users")
public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	public String firstname;

	public String lastname;

	@Valid
	@Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Please provide a valid phone number")
	public String email;

	@Valid
	@Size(min = 8, max = 50, message = "Password must be at least 8 characters long")
	public String password;

	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
	public List<UserCarts> userCart;

	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
	public List<UserOrders> orders;

	@Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Please provide a valid phone number")
	public String phonenumber;

	public String address;

	public UserAccount() {
		// You can initialize any default values here if needed
	}

	public UserAccount(String firstname, String lastname,
			@Valid @Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Please provide a valid phone number") String email,
			@Valid @Size(min = 8, max = 50, message = "Password must be at least 8 characters long") String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}

	public UserAccount(
			@Valid @Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Please provide a valid phone number") String email,
			@Valid @Size(min = 8, max = 50, message = "Password must be at least 8 characters long") String password) {
		this.email = email;
		this.password = password;
	}

	public UserAccount(
			@Valid @Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Please provide a valid phone number") String email) {
		this.email = email;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public List<UserCarts> getUserCart() {
		return userCart;
	}

	public void setUserCart(List<UserCarts> userCart) {
		this.userCart = userCart;
	}

	// public List<UserOrders> getOrders() {
	// return orders;
	// }

	// public void setOrders(List<UserOrders> orders) {
	// this.orders = orders;
	// }

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
