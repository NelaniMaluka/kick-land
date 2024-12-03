package com.examplekicklaandwebsite.KickLaand.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class ProductStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stockId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;

    @NotNull(message = "Size3 must have a value")
    @Min(value = 0, message = "Size3 cannot go below 0")
    private Integer Size3;

    @NotNull(message = "Size4 must have a value")
    @Min(value = 0, message = "Size4 cannot go below 0")
    private Integer Size4;

    @NotNull(message = "Size5 must have a value")
    @Min(value = 0, message = "Size5 cannot go below 0")
    private Integer Size5;

    @NotNull(message = "Size6 must have a value")
    @Min(value = 0, message = "Size6 cannot go below 0")
    private Integer Size6;

    @NotNull(message = "Size7 must have a value")
    @Min(value = 0, message = "Size7 cannot go below 0")
    private Integer Size7;

    @NotNull(message = "Size8 must have a value")
    @Min(value = 0, message = "Size8 cannot go below 0")
    private Integer Size8;

    @NotNull(message = "Size9 must have a value")
    @Min(value = 0, message = "Size9 cannot go below 0")
    private Integer Size9;

    @NotNull(message = "Size10 must have a value")
    @Min(value = 0, message = "Size10 cannot go below 0")
    private Integer Size10;

    @NotNull(message = "Size11 must have a value")
    @Min(value = 0, message = "Size11 cannot go below 0")
    private Integer Size11;

    @NotNull(message = "Size12 must have a value")
    @Min(value = 0, message = "Size12 cannot go below 0")
    private Integer Size12;

    public ProductStock() {}

    // Getters and Setters
    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Integer getSize3() {
        return Size3;
    }

    public void setSize3(Integer size3) {
        Size3 = size3;
    }

    public Integer getSize4() {
        return Size4;
    }

    public void setSize4(Integer size4) {
        Size4 = size4;
    }

    public Integer getSize5() {
        return Size5;
    }

    public void setSize5(Integer size5) {
        Size5 = size5;
    }

    public Integer getSize6() {
        return Size6;
    }

    public void setSize6(Integer size6) {
        Size6 = size6;
    }

    public Integer getSize7() {
        return Size7;
    }

    public void setSize7(Integer size7) {
        Size7 = size7;
    }

    public Integer getSize8() {
        return Size8;
    }

    public void setSize8(Integer size8) {
        Size8 = size8;
    }

    public Integer getSize9() {
        return Size9;
    }

    public void setSize9(Integer size9) {
        Size9 = size9;
    }

    public Integer getSize10() {
        return Size10;
    }

    public void setSize10(Integer size10) {
        Size10 = size10;
    }

    public Integer getSize11() {
        return Size11;
    }

    public void setSize11(Integer size11) {
        Size11 = size11;
    }

    public Integer getSize12() {
        return Size12;
    }

    public void setSize12(Integer size12) {
        Size12 = size12;
    }
}
