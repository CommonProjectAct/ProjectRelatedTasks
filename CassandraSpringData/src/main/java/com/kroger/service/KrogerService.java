package com.kroger.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kroger.Response.UserResponse;
import com.kroger.exceptions.UserNotFoundException;
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
	
	@Autowired 
	UserResponse userResponse;
	
	public UserResponse findAllUser() {
		logger.info("Inside find all service");
		try{
			userResponse.setStatus_code(0);
			userResponse.setStatus_msg("Successful");
			userResponse.setResponse(krogerUserRepo.findAll());
		}catch(Exception e) {
				userResponse.setStatus_code(404);
				userResponse.setStatus_msg("Not found");
				userResponse.setResponse(new UserNotFoundException("Users not found"));
			}
		
		return userResponse;
	}
	
	public UserResponse findByUserId(String user_id) {
		
		logger.info("Insdie find by id service");
		
		Optional<User> user=krogerUserRepo.findById(user_id);
		if(!user.isPresent()) {
			userResponse.setStatus_code(404);
			userResponse.setStatus_msg("Not found");
			userResponse.setResponse(new UserNotFoundException("User Not found for id : "+user_id));
		}else {
				userResponse.setStatus_code(00);
				userResponse.setStatus_msg("Successfull");
				userResponse.setResponse(user);
		}
		return userResponse;
	}
	
	public UserResponse saveUser(User user) {
		 logger.info("Inside save user service"); 
		 try{
			 
			 krogerUserRepo.save(user);
			 userResponse.setStatus_code(0);
			 userResponse.setStatus_msg("Successful");
			 userResponse.setResponse("User id : "+ user.getUser_id() +" inserted successfully");
		 }
		 catch (Exception e) {
			 	logger.info("Exception Caught is "+ e);
			 	userResponse.setStatus_code(404);
			 	userResponse.setStatus_msg("Not found");
			 	userResponse.setResponse("User id : "+user.getUser_id() + " is not saved successfully");
		 }
		 
		 return userResponse;
		
	}
	  
	public UserResponse deleteUser(String user_id) { 
		  logger.info("Inside delete by id service");
		  
			 if(krogerUserRepo.existsById(user_id)) {
				 try {
					   krogerUserRepo.deleteById(user_id);
					   userResponse.setStatus_code(0);
					   userResponse.setStatus_msg("Successful");
					   userResponse.setResponse("User id : "+user_id +" deleted successfully");
				 	}catch (Exception e) {
				 			logger.info("Exception Caught is "+ e);
				 			userResponse.setStatus_code(403);
				 			userResponse.setStatus_msg("Forbidden");
				 			userResponse.setResponse(new UserNotFoundException("User id : "+user_id + " is not deleted"));
				 	 }
			 }else {
				 userResponse.setStatus_code(404);
				 userResponse.setStatus_msg("Not found");
				 userResponse.setResponse("User id : "+user_id+" Not Exist");
			 }
		return userResponse;
	}
	
	public UserResponse updateUser(String user_id,String type) {
		logger.info("Inside update User type by id");
		 if(krogerUserRepo.existsById(user_id)) {
			 try {
				 krogerUserRepo.updateUserTypeById(type, user_id);
				 userResponse.setStatus_code(0);
				 userResponse.setStatus_msg("Successful");
				 userResponse.setResponse("User id : "+ user_id +" Updated successfully");
			 	}catch (Exception e) {
			 		logger.info("Exception Caught is "+ e);
			 		userResponse.setStatus_code(403);
			 		userResponse.setStatus_msg("Exception occured");
			 		userResponse.setResponse(new UserNotFoundException("User id : "+user_id + " is not updated "));
			 	}
		 }else {
				 userResponse.setStatus_code(404);
				 userResponse.setStatus_msg("Not found");
				 userResponse.setResponse("User id : "+user_id+" Not Exist");
		}
			
		return userResponse;
	}
	
	public List<Prod_By_Cat> findByCatName(String cat_name) {
		logger.info("Inside find by cat name service");
		return  krogerCatRepo.findByCatname(cat_name);
	}
	
	public Prod_By_Cat findByCatNameAndId(String cat_name,String cat_id) {
		logger.info("Inside find by cat name service");
		return  krogerCatRepo.findByCatnameAndCatid(cat_name,cat_id);
	}
	
	public List<Products> findByProdName(String prod_name) {
		logger.info("Inside find by prod name Service");
		return krogerProdRepo.findByProdname(prod_name);
				
	}
	
	public Products findByProd_Desc(String prod_desc){
		 logger.info("Inside find by price service");
		 return krogerProdRepo.findByProd_desc(prod_desc);
	}
	
}
