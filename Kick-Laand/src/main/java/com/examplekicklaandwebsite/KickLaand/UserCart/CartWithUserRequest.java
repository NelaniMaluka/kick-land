package com.examplekicklaandwebsite.KickLaand.UserCart;

import javax.validation.constraints.NotNull;

public class CartWithUserRequest {

	@NotNull(message = "ProductId cannot be null")
	private Integer id;

	@NotNull(message = "UserId cannot be null")
	private Integer userId;

	@NotNull(message = "Product name cannot be null")
	private String name;

	@NotNull(message = "Product price cannot be null")
	private double price;

	@NotNull(message = "Product category cannot be null")
	private String category;

	@NotNull(message = "Product quantity cannot be null")
	private Integer quantity;

	@NotNull(message = "Product size cannot be null")
	private String size;

	@NotNull(message = "Product image1 cannot be null")
	private String image1;
	private String image2;
	private String image3;
	private String image4;

	public CartWithUserRequest() {
		// You can initialize any default values here if needed
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double d) {
		this.price = d;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
	}

}
