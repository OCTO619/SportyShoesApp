package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByCname(String name);
}