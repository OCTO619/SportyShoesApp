package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Category;
import com.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
}