package com.examplekicklaandwebsite.KickLaand.Products;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductsController {
    private final ProductsRepository productsRepository;

    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping(path = "/api/public/products")
    public ResponseEntity<?> retrieveProducts() {
        try {
            List<Products> productList = productsRepository.findAll();

            if (productList.isEmpty()) {
                return ResponseEntity.noContent().build(); // Handle empty list
            } else {
                return ResponseEntity.ok(productList);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    
}
