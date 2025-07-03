package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.model.Product;
import com.repository.CategoryRepository;
import com.repository.ProductRepository;

public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	public void deleteProduct(int pid) {
		 productRepository.deleteById(pid);
	}

}
