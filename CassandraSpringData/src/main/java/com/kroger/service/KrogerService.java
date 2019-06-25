package com.kroger.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kroger.exceptions.UserNotFoundException;
import com.kroger.model.ProdByCat;
import com.kroger.model.Products;
import com.kroger.model.User;
import com.kroger.repository.KrogerCatRepo;
import com.kroger.repository.KrogerProdRepo;
import com.kroger.repository.KrogerUserRepo;
import com.kroger.response.UserResponse;

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
	
	private static final String SUCCESSMSG="Successful"; 
	private static final String NOTFOUNDMSG="Not Found"; 
	
	public UserResponse findAllUser() {
		logger.info("Inside find all service");
		try{
			userResponse.setStatusCode(0);
			userResponse.setStatusMsg(SUCCESSMSG);
			userResponse.setResponse(krogerUserRepo.findAll());
		}catch(Exception e) {
				userResponse.setStatusCode(404);
				userResponse.setStatusMsg(NOTFOUNDMSG);
				userResponse.setResponse(new UserNotFoundException("Users not found"));
			}
		
		return userResponse;
	}
	
	public UserResponse findByUserId(String userId) {
		
		logger.info("Insdie find by id service");
		
		Optional<User> user=krogerUserRepo.findById(userId);
		if(!user.isPresent()) {
			userResponse.setStatusCode(404);
			userResponse.setStatusMsg(NOTFOUNDMSG);
			userResponse.setResponse(new UserNotFoundException("User Not found for id : "+userId));
		}else {
				userResponse.setStatusCode(00);
				userResponse.setStatusMsg(SUCCESSMSG);
				userResponse.setResponse(user);
		}
		return userResponse;
	}
	
	public UserResponse findByUserType(String userType) {
		
		logger.info("Insdie find by Type service");
		
		List<User> user=krogerUserRepo.findUserByType(userType);
		if(user.isEmpty()) {
			userResponse.setStatusCode(404);
			userResponse.setStatusMsg(NOTFOUNDMSG);
			userResponse.setResponse(new UserNotFoundException("User Not found for type: "+userType));
		}else {
				userResponse.setStatusCode(00);
				userResponse.setStatusMsg(SUCCESSMSG);
				userResponse.setResponse(user);
		}
		return userResponse;
	}
	
	public UserResponse saveUser(User user) {
		 logger.info("Inside save user service"); 
		 try{
			 
			 krogerUserRepo.save(user);
			 userResponse.setStatusCode(0);
			 userResponse.setStatusMsg(SUCCESSMSG);
			 userResponse.setResponse("User id : "+ user.getUserId() +" inserted successfully");
		 }
		 catch (Exception e) {
			 	userResponse.setStatusCode(404);
			 	userResponse.setStatusMsg(NOTFOUNDMSG);
			 	userResponse.setResponse("User id : "+user.getUserId() + " is not saved successfully due to exception");
		 }
		 
		 return userResponse;
		
	}
	  
	public UserResponse deleteUser(String userId) { 
		  logger.info("Inside delete by id service");
		  
			 if(krogerUserRepo.existsById(userId)) {
				 try {
					   krogerUserRepo.deleteById(userId);
					   userResponse.setStatusCode(0);
					   userResponse.setStatusMsg(SUCCESSMSG);
					   userResponse.setResponse("User id : "+userId +" deleted successfully");
				 	}catch (Exception e) {
				 			logger.info("Exception Caught is "+ e);
				 			userResponse.setStatusCode(403);
				 			userResponse.setStatusMsg("Forbidden");
				 			userResponse.setResponse("User id : "+userId + " is not deleted due to exception");
				 	 }
			 }else {
				 userResponse.setStatusCode(404);
				 userResponse.setStatusMsg(NOTFOUNDMSG);
				 userResponse.setResponse("User id : "+userId+" Not Exist");
			 }
		return userResponse;
	}
	
	public UserResponse updateUser(String userId,String type) {
		logger.info("Inside update User type by id");
		 if(krogerUserRepo.existsById(userId)) {
			 try {
				 krogerUserRepo.updateUserTypeById(type, userId);
				 userResponse.setStatusCode(0);
				 userResponse.setStatusMsg(SUCCESSMSG);
				 userResponse.setResponse("User id : "+ userId +" Updated successfully");
			 	}catch (Exception e) {
			 		logger.info("Exception Caught is "+ e);
			 		userResponse.setStatusCode(403);
			 		userResponse.setStatusMsg("Exception occured");
			 		userResponse.setResponse(new UserNotFoundException("User id : "+userId + " is not updated "));
			 	}
		 }else {
				 userResponse.setStatusCode(404);
				 userResponse.setStatusMsg(NOTFOUNDMSG);
				 userResponse.setResponse("User id : "+userId+" Not Exist");
		}
			
		return userResponse;
	}
	
	public List<ProdByCat> findByCatName(String catName) {
		logger.info("Inside find by cat name service");
		return  krogerCatRepo.findByCatName(catName);
	}
	
	public ProdByCat findByCatNameAndId(String catName,String catId) {
		logger.info("Inside find by cat name service");
		return  krogerCatRepo.findByCatNameAndCatId(catName,catId);
	}
	
	public List<Products> findByProdName(String prodName) {
		logger.info("Inside find by prod name Service");
		return krogerProdRepo.findByProdName(prodName);
				
	}
	
	public Products findByProdDesc(String prodDesc){
		 logger.info("Inside find by price service");
		 return krogerProdRepo.findByProdDesc(prodDesc);
	}
	
}
