package com.examplekicklaandwebsite.KickLaand.dto;

import java.math.BigDecimal;
import java.util.List;

import com.examplekicklaandwebsite.KickLaand.model.ProductStock;

public class ProductResponseDTO {

    private Integer productId;

    private String name;


    private BigDecimal price;


    private String category;

    public List<ProductStock> sizes;

    private String image1;
    private String image2;
    private String image3;
    private String image4;
    
	public ProductResponseDTO(Integer productId,  String name,
			 BigDecimal price,
			String category, List<ProductStock> sizes,
			 String image1, String image2, String image3,
			String image4) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.category = category;
		this.sizes = sizes;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.image4 = image4;
	}
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<ProductStock> getSizes() {
		return sizes;
	}
	public void setSizes(List<ProductStock> sizes) {
		this.sizes = sizes;
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

	@Override
	public String toString() {
		return "ProductsResponseDTO [productId=" + productId + ", name=" + name + ", price=" + price + ", category=" + category
				+ ", sizes=" + sizes + ", image1=" + image1 + ", image2=" + image2 + ", image3=" + image3 + ", image4="
				+ image4 + "]";
	}
    
}
