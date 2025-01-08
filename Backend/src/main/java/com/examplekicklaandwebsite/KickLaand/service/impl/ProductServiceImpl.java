package com.examplekicklaandwebsite.KickLaand.service.impl;

import com.examplekicklaandwebsite.KickLaand.dto.ProductStockDTO;
import com.examplekicklaandwebsite.KickLaand.dto.ProductsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.examplekicklaandwebsite.KickLaand.repository.ProductsRepository;
import com.examplekicklaandwebsite.KickLaand.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductsRepository productsRepository;

    public ProductServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public ResponseEntity<?> getAllProducts() {

        try {
            List<ProductsDTO> productList = productsRepository.findAll().stream()
                    .map(product -> new ProductsDTO(
                            product.getProductName(),
                            product.getProductPrice(),
                            product.getProductCategory(),
                            product.getStock().stream()
                                    .map(stock -> new ProductStockDTO(
                                            stock.getSize3(), stock.getSize4(), stock.getSize5(),
                                            stock.getSize6(), stock.getSize7(), stock.getSize8(),
                                            stock.getSize9(), stock.getSize10(), stock.getSize11(),
                                            stock.getSize12()))
                                    .toList(),
                            product.getImage1(),
                            product.getImage2(),
                            product.getImage3(),
                            product.getImage4()))
                    .toList();

            if (productList.isEmpty()) {
                return ResponseEntity.noContent().build(); // Handle empty list
            }

            return ResponseEntity.ok(productList);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
