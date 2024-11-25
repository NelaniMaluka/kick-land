package com.examplekicklaandwebsite.KickLaand.UserCart;

import javax.validation.constraints.NotNull;

public class UserCartDTO {

	@NotNull(message = "UserId cannot be null")
	private Integer userId;
	
    @NotNull(message = "Product ID cannot be null")
    private Integer productId;

	@NotNull(message = "Product quantity cannot be null")
	private Integer quantity;

	@NotNull(message = "Product size cannot be null")
	private String size;

	public UserCartDTO() {
		// You can initialize any default values here if needed
	}

	public UserCartDTO(@NotNull(message = "UserId cannot be null") Integer userId,
			@NotNull(message = "Product ID cannot be null") Integer productId,
			@NotNull(message = "Product quantity cannot be null") Integer quantity,
			@NotNull(message = "Product size cannot be null") String size) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.size = size;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
