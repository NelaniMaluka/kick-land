package com.examplekicklaandwebsite.KickLaand.Orders;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.examplekicklaandwebsite.KickLaand.User.UserAccount;
import com.examplekicklaandwebsite.KickLaand.UserCart.UserCarts;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserOrders {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer orderId;

		@ManyToOne
		@JoinColumn(name = "userId")
		private UserAccount userId;

		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		@JoinColumn(name= "orderId")
		List <UserCarts> products;
		
		@Valid
		@Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Please provide a valid phone number")
		private String userEmail;
		
		@NotNull
		private String userAddress;
		
		@Pattern(
		        regexp = "^\\+?[0-9\\-\\s]*$", 
		        message = "Please provide a valid phone number"
		    )
		private String userPhoneNumber;

		public UserOrders() {
			// You can initialize any default values here if needed
		}

		public Integer getOrderId() {
			return orderId;
		}

		public void setOrderId(Integer orderId) {
			this.orderId = orderId;
		}

		public UserAccount getUserId() {
			return userId;
		}

		public void setUserId(UserAccount userId) {
			this.userId = userId;
		}

		public List<UserCarts> getProducts() {
			return products;
		}

		public void setProducts(List<UserCarts> products) {
			this.products = products;
		}

		public String getUserEmail() {
			return userEmail;
		}

		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}

		public String getUserAddress() {
			return userAddress;
		}

		public void setUserAddress(String userAddress) {
			this.userAddress = userAddress;
		}

		public String getUserPhoneNumber() {
			return userPhoneNumber;
		}

		public void setUserPhoneNumber(String userPhoneNumber) {
			this.userPhoneNumber = userPhoneNumber;
		}

}
