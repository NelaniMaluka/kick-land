package com.examplekicklaandwebsite.KickLaand.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examplekicklaandwebsite.KickLaand.model.Products;
import com.examplekicklaandwebsite.KickLaand.repository.ProductsRepository;
import com.examplekicklaandwebsite.KickLaand.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }
}
