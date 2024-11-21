package com.examplekicklaandwebsite.KickLaand.Products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/api/public/products")
    public ResponseEntity<?> retrieveProducts() {
        try {
            List<Products> productList = productService.getAllProducts();

            if (productList.isEmpty()) {
                return ResponseEntity.noContent().build(); // Handle empty list
            } else {
            	
            	List<ProductResponseDTO> responseDTOlist = productList.stream()
            			.map(this::getProductsWithoutPriceUrl)
            			.toList();
                return ResponseEntity.ok(responseDTOlist);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    
    private ProductResponseDTO getProductsWithoutPriceUrl(Products product){
    	return new ProductResponseDTO(
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

