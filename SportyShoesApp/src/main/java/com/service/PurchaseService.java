package com.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Purchase;
import com.repository.PurchaseRepository;

@Service
public class PurchaseService {
	@Autowired
	private PurchaseRepository purchaseRepository;
	public List<Purchase> getAllPurchases(){
		return purchaseRepository.findAll();
	}
	public List<Purchase> filterByDate(LocalDate start, LocalDate end){
		return purchaseRepository.findByPurchaseDateBetween(start,end);
	}
	public List<Purchase> filterByCategory(String categoryName){
		return purchaseRepository.findByProduct_Category_Name(categoryName);
	}
	
}
