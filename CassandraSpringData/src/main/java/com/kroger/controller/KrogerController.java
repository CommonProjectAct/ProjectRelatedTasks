package com.kroger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kroger.model.ProdByCat;
import com.kroger.model.Products;
import com.kroger.model.User;
import com.kroger.response.UserResponse;
import com.kroger.service.KrogerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class KrogerController {
	
	
	 
	@Autowired
	KrogerService krogerService;
	
	@GetMapping("/findalluser")
	public UserResponse findAllUser() {
		
		log.info("Inside of find all service");
		return krogerService.findAllUser();
	}
	
	@GetMapping("/finduserbyid/{userId}")
	public UserResponse findByUserId(@PathVariable("userId") String userId) {
		
		log.info("Insdie find by id service");
		return krogerService.findByUserId(userId);
	}
	
	@GetMapping("/finduserbytype/{userType}")
	public UserResponse findByUserType(@PathVariable("userType") String userType) {
		
		log.info("Insdie find by type service");
		return krogerService.findByUserType(userType);
	}
	
	 @PostMapping("/saveuser") 
	 public UserResponse saveUser(@RequestBody User user) {
		
		 log.info("Inside save user service"); 
		 return krogerService.saveUser(user); 
	}
	
	 @DeleteMapping("/deleteuser/{id}") 
	 public UserResponse deleteUser(@PathVariable("id") String userId) { 
		
		 log.info("Inside delete by id service");
		 return  krogerService.deleteUser(userId); 
	}
	 
	 @PutMapping("/updateuser/{userId}/{type}")
	 public UserResponse updateUser(@PathVariable("userId") String userId,@PathVariable("type") String type) {
		 
		 log.info("Inside update user service");
		 return krogerService.updateUser(userId,type);
	 }
	 
	 @GetMapping("/findbyprod_desc/{prodDesc}")
	 public Products findByProdDesc(@PathVariable("prodDesc") String prodDesc){
		 
		 log.info("Inside find by prodct description");
		 return krogerService.findByProdDesc(prodDesc);
	 }	 
	 
	 @GetMapping("/findbycat/{catName}")
	 public List<ProdByCat> findByCatName(@PathVariable("catName") String catName) {
		
		log.info("Inside find by cat name service");
		return  krogerService.findByCatName(catName);
	 }
	 
	
	 @GetMapping("findbyprod/{prodName}")
	 public List<Products> findByProdName(@PathVariable("prodName") String prodName) {
		
		log.info("Inside find by prod name Service");
		return krogerService.findByProdName(prodName);
				
	 }
	
	
}
