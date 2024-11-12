package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.repo.ProductRepo;

@SpringBootApplication
public class ManyToManyApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ProductRepo productRepo;

	public static void main(String[] args) {
		SpringApplication.run(ManyToManyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product product1 = new Product();
		product1.setpId("pid1");
		product1.setProductName("Iphone 14 max pro");
		
		Product product2 = new Product();
		product2.setpId("pid2");
		product2.setProductName("Samsung s22 ultra");
		
		Product product3 = new Product();
		product3.setpId("pid3");
		product3.setProductName("Samsung TV12341");
		
		Category category1 = new Category();
		category1.setcId("cid1");
		category1.setTitle("Electronics");
		
		Category category2 = new Category();
		category2.setcId("cid2");
		category2.setTitle("Mobile Phone");
		
		List<Product> category1Products = category1.getProducts();
		category1Products.add(product1);
		category1Products.add(product2);
		category1Products.add(product3);
		
		List<Product> category2Products = category2.getProducts();
		category2Products.add(product1);
		category2Products.add(product2);
		
		categoryRepo.save(category1);
		categoryRepo.save(category2);
		
	}

}
