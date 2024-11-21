package com.examplekicklaandwebsite.KickLaand.UserCart;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.examplekicklaandwebsite.KickLaand.Orders.UserOrders;
import com.examplekicklaandwebsite.KickLaand.User.UserAccount;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCarts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userCartId;

	@ManyToOne(cascade = CascadeType.ALL)
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
		return  productSize;
	}

	public void setProductSize(String  productSize) {
		this. productSize =  productSize;
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
