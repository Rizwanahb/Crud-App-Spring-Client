package com.example.sbcruddemo;

import com.example.sbcruddemo.entity.Product;
import com.example.sbcruddemo.service.ProductService;
import com.example.sbcruddemo.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SbCrudDemoApplicationTests {

	@Autowired
	private ProductService productService;

	@Test
	void saveProductTest() {
		Product product = new Product();
		product.setpName("Keychain");
		product.setpType("Toys");
		product.setpPrice(12.5F);
		productService.saveProduct(product);
	}

	@Test
	void getAllProoductTest(){
		List<Product> products= productService.getAllProducts();
		System.out.println(products);
	}

	@Test
	void DeleteProductByIdTest(){
		Integer pNo=1;
		productService.deleteProduct(1);
	}

	@Test
	void getProductByIdTest(){
		Integer pNo=2;
		productService.getProductById(2);
	}

}
