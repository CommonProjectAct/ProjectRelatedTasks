package com.kroger.service;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kroger.model.Prod_By_Cat;
import com.kroger.model.Products;
import com.kroger.model.User;
import com.kroger.repository.KrogerCatRepo;
import com.kroger.repository.KrogerProdRepo;
import com.kroger.repository.KrogerUserRepo;

@Service
public class KrogerService {
	private static final Logger logger = LogManager.getLogger(KrogerService.class);
	
	@Autowired
	KrogerUserRepo krogerUserRepo;
	
	@Autowired
	KrogerCatRepo krogerCatRepo;
	
	@Autowired
	KrogerProdRepo krogerProdRepo;
	
	public Iterable<User> findAllUser() {
		logger.info("Inside find all service");
		return krogerUserRepo.findAll();
	}
	
	public Optional<User> findByUserId(String user_id) {
		logger.info("Insdie find by id service");
		return krogerUserRepo.findById(user_id);
	}
	
	public Prod_By_Cat findByCatName(String cat_name) {
		logger.info("Inside find by cat name service");
		return  krogerCatRepo.findByName(cat_name);
	}
	
	public List<Products> findByProdName(String prod_name) {
		logger.info("Inside find by prod name Service");
		return krogerProdRepo.searchByName(prod_name);
				
	}
	
	public void saveUser(User user) {
		 logger.info("Inside save user service"); 
		 krogerUserRepo.save(user); 
	}
	  
	public void deleteUser(String user_id) { 
		  logger.info("Inside delete by id service");
		  krogerUserRepo.deleteById(user_id); 
	}
	
	public List<Products> findByProd_Desc(String prod_desc){
		 logger.info("Inside find by price service");
		 return krogerProdRepo.findByProd_desc(prod_desc);
	}
	
}
