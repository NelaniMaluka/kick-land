package com.examplekicklaandwebsite.KickLaand.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCartDTO {

	@NotNull(message = "Product ID cannot be null")
	private Integer productId;

	@NotNull(message = "Product quantity cannot be null")
	private Integer quantity;

	@NotNull(message = "Product size cannot be null")
	private String size;

	@NotNull(message = "Product Price cannot be null")
	private Integer price;
}
