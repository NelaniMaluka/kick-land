package com.examplekicklaandwebsite.KickLaand.Products;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull ( message = "Product name cannot be null")
    private String name;

    @NotNull ( message = "Product price cannot be null")
    private BigDecimal price;

    @NotNull ( message = "Product category cannot be null")
    private String category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    public List<ProductSizes> sizes;

    @NotNull ( message = "Product image1 cannot be null")
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    
    @NotNull
    private String priceUrl;

	public Products() {
	    // default constructor body
	}


	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<ProductSizes> getSizes() {
        return sizes;
    }

    public void setSizes(List<ProductSizes> sizes) {
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
    
	public String getPriceUrl() {
		return priceUrl;
	}


	public void setPriceUrl(String priceUrl) {
		this.priceUrl = priceUrl;
	}

}
