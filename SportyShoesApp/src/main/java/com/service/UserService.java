package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.User;
import com.repository.UserRepository;
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	public List<User> searchUsers(String keyword){
		return userRepository.findByNameContainingIgnoreCase(keyword);
	}
	
}
