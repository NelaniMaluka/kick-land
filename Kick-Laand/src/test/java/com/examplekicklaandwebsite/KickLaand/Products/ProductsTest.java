package com.examplekicklaandwebsite.KickLaand.Products;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProductsTest {

	@Mock
	ProductsRepository productRepository;

	@InjectMocks
	ProductsController productsController;

	@Test
	@Sql("/data.sql") // Specify the path to your SQL script
	void getSuccessProduct_information() {

		assertEquals(ResponseEntity.ok(), productsController.retrieveProducts());
	}

	@Test
	@Sql("/data.sql") // Specify the path to your SQL script
	void getEmptyProduct_information() {
		assertEquals(ResponseEntity.noContent().build(), productsController.retrieveProducts());
	}

}
