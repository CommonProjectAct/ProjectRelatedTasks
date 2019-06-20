package com.kroger.controller;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kroger.model.Prod_By_Cat;
import com.kroger.model.Products;
import com.kroger.model.User;
import com.kroger.service.KrogerService;

@RestController
public class KrogerController {
	
	private static final Logger logger = LogManager.getLogger(KrogerController.class);
	 
	@Autowired
	KrogerService krogerService;
	
	@GetMapping("/findall")
	public Iterable<User> findAllUser() {
		logger.info("Inside find all service");
		return krogerService.findAllUser();
	}
	
	@GetMapping("/findbyid/{uid}")
	public Optional<User> findByUserId(@PathVariable("uid") String user_id) {
		logger.info("Insdie find by id service");
		return krogerService.findByUserId(user_id);
	}
	
	 @PostMapping("/save") 
	 public void saveUser(@RequestBody User user) {
		 logger.info("Inside save user service"); 
		 krogerService.saveUser(user); 
		 }
	
	 @RequestMapping("/delete/{id}") 
	 public void deleteUser(@PathVariable("id") String user_id) { 
		  logger.info("Inside delete by id service");
		  krogerService.deleteUser(user_id); 
		 }
	 
	 @GetMapping("/findbycat/{cname}")
	 public Prod_By_Cat findByCatName(@PathVariable("cname") String cat_name) {
		logger.info("Inside find by cat name service");
		return  krogerService.findByCatName(cat_name);
	 }
	
	 @GetMapping("findbyprod/{pname}")
	 public List<Products> findByProdName(@PathVariable("pname") String prod_name) {
		logger.info("Inside find by prod name Service");
		return krogerService.findByProdName(prod_name);
				
	 }
	
	 @GetMapping("/findbyprod_desc/{prod_desc}")
	 public List<Products> findByProd_Desc(@PathVariable("prod_desc") String prod_desc){
		 logger.info("Inside find by price");
		 logger.debug("Inside debugger");
		 logger.warn("inside warn");
		 logger.error("Inside Error");
		 return krogerService.findByProd_Desc(prod_desc);
	 }
	 
}
