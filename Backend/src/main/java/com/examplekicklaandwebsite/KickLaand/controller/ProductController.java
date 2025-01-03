package com.examplekicklaandwebsite.KickLaand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examplekicklaandwebsite.KickLaand.response.ProductResponse;
import com.examplekicklaandwebsite.KickLaand.model.Products;
import com.examplekicklaandwebsite.KickLaand.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<?> retrieveProducts() {
        try {
            List<Products> productList = productService.getAllProducts();

            if (productList.isEmpty()) {
                return ResponseEntity.noContent().build(); // Handle empty list
            } else {
            	
            	List<ProductResponse> responseDTOlist = productList.stream()
            			.map(this::getProductsWithoutPriceUrl)
            			.toList();
                return ResponseEntity.ok(responseDTOlist);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    private ProductResponse getProductsWithoutPriceUrl(Products product){
    	return new ProductResponse(
    				product.getProductId(),
    				product.getProductName(),
    				product.getProductPrice(),
    				product.getProductCategory(),
    				product.getStock(),
    				product.getImage1(),
    				product.getImage2(),
    				product.getImage3(),
    				product.getImage4()
    			);
    			
    }
}

