package com.kroger.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kroger.model.User;
import com.kroger.repository.UserDetailRepoImpl;
import com.kroger.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserDetailRepoImpl userDetailRepoImpl;
	
	@GetMapping("/findById/{id}")
	public Optional<User> findById(@PathVariable("id") String user_id) {
		return userRepository.findById(user_id);
	}
	
	@GetMapping("/findByQBid/{user_id}")
	public User findByIdQB(@PathVariable("user_id") String user_id){
		return userDetailRepoImpl.getCustomerDetails(user_id);
	}
	
	@GetMapping("/findByNQid")
	public User findByIdNQ(){
		return userDetailRepoImpl.getUserDetails();
	}
}
