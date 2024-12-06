package com.examplekicklaandwebsite.KickLaand.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;


@Entity
@Getter
@Builder
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

}
