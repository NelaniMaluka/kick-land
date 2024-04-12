package com.examplekicklaandwebsite.KickLaand.User;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.examplekicklaandwebsite.KickLaand.Orders.UserOrders;
import com.examplekicklaandwebsite.KickLaand.Roles.Roles;
import com.examplekicklaandwebsite.KickLaand.UserCart.Cart;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
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
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Roles> roles = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	public List<Cart> cart;
	
	//@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	//public List<UserOrders> orders;

	@Pattern(
		        regexp = "^\\+?[0-9\\-\\s]*$", 
		        message = "Please provide a valid phone number"
		    )
	public String phonenumber;

	public String address;

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

	public List<Roles> getRoles() {
		return roles;
	}


	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}
	
	 //public List<UserOrders> getOrders() {
	//	return orders;
	//}


	//public void setOrders(List<UserOrders> orders) {
	//	this.orders = orders;
	//}

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
