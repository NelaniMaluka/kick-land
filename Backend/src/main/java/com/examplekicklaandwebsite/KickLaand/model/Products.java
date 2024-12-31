package com.examplekicklaandwebsite.KickLaand.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(nullable = false)
    @NotNull(message = "Product name cannot be null")
    private String productName;

    @Column(nullable = false)
    @NotNull(message = "Product price cannot be null")
    private BigDecimal productPrice;

    @Column(nullable = false)
    @NotNull(message = "Product category cannot be null")
    private String productCategory;

    @Column(nullable = false)
    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    @NotNull(message = "Stock List cannot be null")
    public List<ProductStock> stock;

    @Column(nullable = false)
    @NotNull(message = "Product image1 cannot be null")
    private String image1;

    @Column(nullable = false)
    @NotNull(message = "Product image2 cannot be null")
    private String image2;

    @Column(nullable = false)
    @NotNull(message = "Product image3 cannot be null")
    private String image3;

    @Column(nullable = false)
    @NotNull(message = "Product image4 cannot be null")
    private String image4;

    @NotNull
    private String priceUrl;

}
