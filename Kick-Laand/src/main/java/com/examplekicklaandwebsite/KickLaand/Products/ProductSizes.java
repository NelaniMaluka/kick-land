package com.examplekicklaandwebsite.KickLaand.Products;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProductSizes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Products product;
	
	private Integer Size3;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
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
	private Integer Size4;
	private Integer Size5;
	private Integer Size6;
	private Integer Size7;
	private Integer Size8;
	private Integer Size9;
	private Integer Size10;
	private Integer Size11;
	private Integer Size12;
	
}
