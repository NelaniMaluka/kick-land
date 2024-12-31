package com.examplekicklaandwebsite.KickLaand.response;

import java.math.BigDecimal;
import java.util.List;

import com.examplekicklaandwebsite.KickLaand.model.ProductStock;
import lombok.Data;

@Data
public class ProductResponse {

	private Integer productId;

	private String name;

	private BigDecimal price;

	private String category;

	public List<ProductStock> sizes;

	private String image1;
	private String image2;
	private String image3;
	private String image4;

	public ProductResponse(Integer productId, String name,
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

	@Override
	public String toString() {
		return "ProductsResponseDTO [productId=" + productId + ", name=" + name + ", price=" + price + ", category="
				+ category
				+ ", sizes=" + sizes + ", image1=" + image1 + ", image2=" + image2 + ", image3=" + image3 + ", image4="
				+ image4 + "]";
	}

}
