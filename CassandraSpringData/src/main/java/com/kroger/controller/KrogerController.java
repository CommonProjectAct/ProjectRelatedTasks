package com.kroger.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kroger.Response.UserResponse;
import com.kroger.model.Prod_By_Cat;
import com.kroger.model.Products;
import com.kroger.model.User;
import com.kroger.service.KrogerService;

@RestController
public class KrogerController {
	
	private static final Logger logger = LogManager.getLogger(KrogerController.class);
	 
	@Autowired
	KrogerService krogerService;
	
	@GetMapping("/findalluser")
	public UserResponse findAllUser() {
		
		logger.info("Inside of find all service");
		UserResponse userResponse=krogerService.findAllUser();
		return userResponse;
	}
	
	@GetMapping("/finduserbyid/{uid}")
	public UserResponse findByUserId(@PathVariable("uid") String user_id) {
		
		logger.info("Insdie find by id service");
		return krogerService.findByUserId(user_id);
	}
	
	 @PostMapping("/saveuser") 
	 public UserResponse saveUser(@RequestBody User user) {
		
		 logger.info("Inside save user service"); 
		 return krogerService.saveUser(user); 
	}
	
	 @DeleteMapping("/deleteuser/{id}") 
	 public UserResponse deleteUser(@PathVariable("id") String user_id) { 
		
		 logger.info("Inside delete by id service");
		 return  krogerService.deleteUser(user_id); 
	}
	 
	 @PutMapping("/updateuser/{user_id}/{type}")
	 public UserResponse updateUser(@PathVariable("user_id") String user_id,@PathVariable("type") String type) {
		 
		 logger.info("Inside update user service");
		 return krogerService.updateUser(user_id,type);
	 }
	 
	 @GetMapping("/findbyprod_desc/{prod_desc}")
	 public Products findByProd_Desc(@PathVariable("prod_desc") String prod_desc){
		 
		 logger.info("Inside find by prodct description");
		 return krogerService.findByProd_Desc(prod_desc);
	 }	 
	 
	 @GetMapping("/findbycat/{catname}")
	 public List<Prod_By_Cat> findByCatName(@PathVariable("catname") String catname) {
		
		 logger.info("Inside find by cat name service");
		return  krogerService.findByCatName(catname);
	 }
	 
	 @GetMapping("/findbycatname_and_id/{catname}/{cid}")
	 public Prod_By_Cat findByCatNameAndId(@PathVariable("catname") String catname,@PathVariable("cid") String cid) {
			
		 	logger.info("Inside find by cat name service");
			return  krogerService.findByCatNameAndId(catname, cid);
	}
	
	 @GetMapping("findbyprod/{pname}")
	 public List<Products> findByProdName(@PathVariable("pname") String prod_name) {
		
		 logger.info("Inside find by prod name Service");
		return krogerService.findByProdName(prod_name);
				
	 }
	
	
}
