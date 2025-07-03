package com.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByPurchaseDateBetween(LocalDate start, LocalDate end);
    List<Purchase> findByProduct_Category_Name(String categoryName);
}
