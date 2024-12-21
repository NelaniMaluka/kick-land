package com.examplekicklaandwebsite.KickLaand.Products;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.examplekicklaandwebsite.KickLaand.controller.ProductController;
import com.examplekicklaandwebsite.KickLaand.response.ProductResponse;
import com.examplekicklaandwebsite.KickLaand.model.Products;
import com.examplekicklaandwebsite.KickLaand.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class ProductsControllerTest {

    // Mock the ProductService (which is used by the controller)
    @Mock
    private ProductService productService;

    // Inject the mocked ProductService into the ProductsController
    @InjectMocks
    private ProductController productsController;

    // Test case to verify that products are returned successfully
    @Test
    @Sql("/data.sql") // Specify the path to your SQL script (optional)
    void getSuccessProduct_information() throws JsonProcessingException {
        // Mock the service layer to return a list of products
        List<Products> mockProducts = List.of(
            new Products(1, "Product 1", new BigDecimal("100.00"), "Category1", null, "image1.jpg", "image2.jpg", "image3.jpg", "image4.jpg", "http://price.url/1"),
            new Products(2, "Product 2", new BigDecimal("150.00"), "Category2", null, "image1.jpg", "image2.jpg", "image3.jpg", "image4.jpg", "http://price.url/2")
        );

        // Use doReturn() to mock the return value of productService.getAllProducts()
        doReturn(mockProducts).when(productService).getAllProducts();

        // Call the controller method
        ResponseEntity<?> responseEntity = productsController.retrieveProducts();

        // Expected DTO List
        List<ProductResponse> expectedResponse = List.of(
            new ProductResponse(1, "Product 1", new BigDecimal("100.00"), "Category1", null, "image1.jpg", "image2.jpg", "image3.jpg", "image4.jpg"),
            new ProductResponse(2, "Product 2", new BigDecimal("150.00"), "Category2", null, "image1.jpg", "image2.jpg", "image3.jpg", "image4.jpg")
        );

        // Serialize the actual response body and expected response to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String actualJson = objectMapper.writeValueAsString(responseEntity.getBody());
        String expectedJson = objectMapper.writeValueAsString(expectedResponse);

        // Assert that the serialized JSON strings are equal
        assertEquals(expectedJson, actualJson);
    }

    // Test case to verify that an empty list of products returns a 204 No Content response
    @Test
    @Sql("/data.sql") // Specify the path to your SQL script (optional)
    void getEmptyProduct_information() {
        // Mock the behavior of the service layer to return an empty list of products using doReturn()
        doReturn(Collections.emptyList()).when(productService).getAllProducts();

        // Call the controller method and capture the response
        ResponseEntity<?> responseEntity = productsController.retrieveProducts();

        // Assert the response is a 204 No Content response
        assertEquals(ResponseEntity.noContent().build(), responseEntity);
    }
}
