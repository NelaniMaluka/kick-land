package com.examplekicklaandwebsite.KickLaand.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCarts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userCartId;

	@ManyToOne
	@JoinColumn(name = "userId")
	private UserAccount userId;

	@NotNull(message = "Product ID cannot be null")
	private Integer productId;

	@NotNull(message = "Product size cannot be null")
	private String productSize;

	@NotNull(message = "Product quantity cannot be null")
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "orderId", insertable = false, updatable = false)
	private UserOrders order;

	public UserCarts() {
		// You can initialize any default values here if needed
	}

	public Integer getUserCartId() {
		return userCartId;
	}

	public void setUserCartId(Integer userCartId) {
		this.userCartId = userCartId;
	}

	public UserAccount getUserId() {
		return userId;
	}

	public void setUserId(UserAccount userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public UserOrders getOrder() {
		return order;
	}

	public void setOrder(UserOrders order) {
		this.order = order;
	}

}
