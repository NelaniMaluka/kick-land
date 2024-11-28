package com.examplekicklaandwebsite.KickLaand.Products;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;


@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @NotNull(message = "Product name cannot be null")
    private String productName;

    @NotNull(message = "Product price cannot be null")
    private BigDecimal productPrice;

    @NotNull(message = "Product category cannot be null")
    private String productCategory;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    public List<ProductStock> stock;

    @NotNull(message = "Product image1 cannot be null")
    private String image1;
    private String image2;
    private String image3;
    private String image4;

    @NotNull
    private String priceUrl;

    public Products() {}

    // Parameterized constructor
    public Products(Integer productId, String productName, BigDecimal productPrice, String productCategory, List<ProductStock> stock, String image1, String image2, String image3, String image4, String priceUrl) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.stock = stock;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.priceUrl = priceUrl;
    }

    // Getters and Setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public List<ProductStock> getStock() {
        return stock;
    }

    public void setStock(List<ProductStock> stock) {
        this.stock = stock;
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
