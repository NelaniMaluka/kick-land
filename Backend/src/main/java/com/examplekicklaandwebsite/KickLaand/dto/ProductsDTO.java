package com.examplekicklaandwebsite.KickLaand.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO {
    private Integer id;
    private String name;
    private BigDecimal price;
    private String category;
    private List<ProductStockDTO> stock;
    private String image1;
    private String image2;
    private String image3;
    private String image4;

}

