package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Category;
import com.model.Product;
import com.repository.CategoryRepository;
import com.repository.ProductRepository;
@Service
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
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}

}
