package com.kroger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kroger.model.User;
import com.kroger.repository.KrogerUserRepo;

@RestController
public class UserController {

	
	@Autowired
	KrogerUserRepo krogerUserRepo;
	
	@GetMapping("/findByQBid/{user_id}")
	public User findByIdQB(@PathVariable("user_id") String user_id) {
		return krogerUserRepo.getCustomerDetails(user_id);
	}
	
	@GetMapping("/findUserById/{userid}")
	public User findByUserId(@PathVariable("userid") String user_id) {
		return krogerUserRepo.getUserDetails(user_id);
	}
	
	@RequestMapping("/deleteUserById/{userid}")
	public void deleteByUserId(@PathVariable("userid") String user_id) {
		krogerUserRepo.deleteUserById(user_id);
	}
	
	@PostMapping("/insertUser")
	public void insertUser(@RequestBody User user) {
		krogerUserRepo.insertUser(user);
	}
	
	@PostMapping("/updateUser/{userid}/{type}")
	public void updateUser(@PathVariable("userid") String userid,@PathVariable("type") String type) {
		krogerUserRepo.updateUser(userid, type);
	}
	
}
