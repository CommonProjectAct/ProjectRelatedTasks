package com.kroger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kroger.model.User;
import com.kroger.repository.KrogerUserRepo;
import com.kroger.response.UserResponse;

@RestController
public class UserController {

	
	@Autowired
	KrogerUserRepo krogerUserRepo;
	
	@GetMapping("/findByQBid/{userId}")
	public User findByIdQB(@PathVariable("userId") String userId) {
		return krogerUserRepo.getCustomerDetails(userId);
	}
	
	@GetMapping("/findUserById/{userId}")
	public UserResponse findByUserId(@PathVariable("userId") String userId) {
		return krogerUserRepo.getUserDetails(userId);
	}
	
	@DeleteMapping("/deleteUserById/{userId}")
	public UserResponse deleteByUserId(@PathVariable("userId") String userId) {
		return krogerUserRepo.deleteUserById(userId);
	}
	
	@PostMapping("/insertUser")
	public UserResponse insertUser(@RequestBody User user) {
		return krogerUserRepo.insertUser(user);
	}
	
	@PostMapping("/updateUser/{userId}/{type}")
	public UserResponse updateUser(@PathVariable("userId") String userid,@PathVariable("type") String type) {
		return krogerUserRepo.updateUser(userid, type);
	}
	
}
