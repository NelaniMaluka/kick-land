package com.examplekicklaandwebsite.KickLaand.Orders;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.examplekicklaandwebsite.KickLaand.User.UserAccount;
import com.examplekicklaandwebsite.KickLaand.UserCart.Cart;
import com.fasterxml.jackson.annotation.JsonInclude;

//@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserOrders {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;

		@ManyToOne
		@JoinColumn(name = "user_id")
		private UserAccount user;

		@NotNull
		List <Cart> products;

		public UserOrders() {
			// You can initialize any default values here if needed
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public UserAccount getUser() {
			return user;
		}

		public void setUser(UserAccount user) {
			this.user = user;
		}

		public List<Cart> getProducts() {
			return products;
		}

		public void setProducts(List<Cart> products) {
			this.products = products;
		}

}
