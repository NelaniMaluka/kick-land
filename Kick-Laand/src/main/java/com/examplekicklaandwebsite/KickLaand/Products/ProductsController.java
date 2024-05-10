package com.examplekicklaandwebsite.KickLaand.Products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductsController {

    private final ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/api/public/products")
    public ResponseEntity<?> retrieveProducts() {
        try {
            List<Products> productList = productService.getAllProducts();

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

