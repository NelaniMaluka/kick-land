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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
