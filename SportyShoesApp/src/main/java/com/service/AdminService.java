package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Admin;
import com.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Login function
    public Admin login(String username, String password) {
        return adminRepository.findByUsernameAndPassword(username, password);
    }

    // Find admin by username
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    // Update admin (used for changing password)
    public void updateAdmin(Admin admin) {
        adminRepository.save(admin);
    }
}
