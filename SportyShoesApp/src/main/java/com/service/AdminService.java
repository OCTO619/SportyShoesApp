package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Admin;
import com.repository.AdminRepository;

@Service
public class AdminService {
@Autowired
private AdminRepository adminRepository;
	public Admin login(String username,String password) {
	return adminRepository.findByUsernameAndPassword(username, password);
	}
	

}
