package com.examplekicklaandwebsite.KickLaand.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter

@Builder
public class UserCartDTO {

	@NotNull(message = "UserId cannot be null")
	private Integer userId;
	
    @NotNull(message = "Product ID cannot be null")
    private Integer productId;

	@NotNull(message = "Product quantity cannot be null")
	private Integer quantity;

	@NotNull(message = "Product size cannot be null")
	private String size;

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

}
