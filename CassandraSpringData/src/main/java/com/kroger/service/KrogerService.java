package com.kroger.service;

import java.util.List;
import java.util.Optional;

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
import com.kroger.util.CommonConstants;
import com.kroger.util.ServiceErrorCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KrogerService {

	@Autowired
	KrogerUserRepo krogerUserRepo;

	@Autowired
	KrogerCatRepo krogerCatRepo;

	@Autowired
	KrogerProdRepo krogerProdRepo;

	@Autowired
	UserResponse userResponse;

	private static final String SUCCESSMSG = "Successful";
	private static final String NOTFOUNDMSG = "Not Found";

	public List<User> findAllUser() {
		log.info("Inside find all service");
		/*
		 * try{ userResponse.setStatusCode(0); userResponse.setStatusMsg(SUCCESSMSG);
		 * userResponse.setResponse(krogerUserRepo.findAll()); }catch(Exception e) {
		 * userResponse.setStatusCode(404); userResponse.setStatusMsg(NOTFOUNDMSG);
		 * userResponse.setResponse(new UserNotFoundException("Users not found")); }
		 */
		return (List<User>) krogerUserRepo.findAll();

	}

	public User findByUserId(String userId) throws com.kroger.exception.UserNotFoundException {

		return krogerUserRepo.findById(userId)
				.orElseThrow(() -> new com.kroger.exception.UserNotFoundException(
						CommonConstants.DATA_VALIDATION_FAILED,
						String.format(CommonConstants.NOT_A_VALID_USER_ID, userId), ServiceErrorCode.GENERIC_ERROR));

	}

	public UserResponse findByUserType(String userType) {

		log.info("Insdie find by Type service");

		List<User> user = krogerUserRepo.findUserByType(userType);
		if (user.isEmpty()) {
			userResponse.setStatusCode(404);
			userResponse.setStatusMsg(NOTFOUNDMSG);
			userResponse.setResponse(new UserNotFoundException("User Not found for type: " + userType));
		} else {
			userResponse.setStatusCode(00);
			userResponse.setStatusMsg(SUCCESSMSG);
			userResponse.setResponse(user);
		}
		return userResponse;
	}

	public UserResponse saveUser(User user) {
		log.info("Inside save user service");
		try {

			krogerUserRepo.save(user);
			userResponse.setStatusCode(0);
			userResponse.setStatusMsg(SUCCESSMSG);
			userResponse.setResponse("User id : " + user.getUserId() + " inserted successfully");
		} catch (Exception e) {
			userResponse.setStatusCode(404);
			userResponse.setStatusMsg(NOTFOUNDMSG);
			userResponse.setResponse("User id : " + user.getUserId() + " is not saved successfully due to exception");
		}

		return userResponse;

	}

	public UserResponse deleteUser(String userId) {
		log.info("Inside delete by id service");

		if (krogerUserRepo.existsById(userId)) {
			try {
				krogerUserRepo.deleteById(userId);
				userResponse.setStatusCode(0);
				userResponse.setStatusMsg(SUCCESSMSG);
				userResponse.setResponse("User id : " + userId + " deleted successfully");
			} catch (Exception e) {
				log.info("Exception Caught is " + e);
				userResponse.setStatusCode(403);
				userResponse.setStatusMsg("Forbidden");
				userResponse.setResponse("User id : " + userId + " is not deleted due to exception");
			}
		} else {
			userResponse.setStatusCode(404);
			userResponse.setStatusMsg(NOTFOUNDMSG);
			userResponse.setResponse("User id : " + userId + " Not Exist");
		}
		return userResponse;
	}

	public UserResponse updateUser(String userId, String type) {
		log.info("Inside update User type by id");
		if (krogerUserRepo.existsById(userId)) {
			try {
				krogerUserRepo.updateUserTypeById(type, userId);
				userResponse.setStatusCode(0);
				userResponse.setStatusMsg(SUCCESSMSG);
				userResponse.setResponse("User id : " + userId + " Updated successfully");
			} catch (Exception e) {
				log.info("Exception Caught is " + e);
				userResponse.setStatusCode(403);
				userResponse.setStatusMsg("Exception occured");
				userResponse.setResponse(new UserNotFoundException("User id : " + userId + " is not updated "));
			}
		} else {
			userResponse.setStatusCode(404);
			userResponse.setStatusMsg(NOTFOUNDMSG);
			userResponse.setResponse("User id : " + userId + " Not Exist");
		}

		return userResponse;
	}

	public List<ProdByCat> findByCatName(String catName) {
		log.info("Inside find by cat name service");
		return krogerCatRepo.findByCatName(catName);
	}

	public ProdByCat findByCatNameAndId(String catName, String catId) {
		log.info("Inside find by cat name service");
		return krogerCatRepo.findByCatNameAndCatId(catName, catId);
	}

	public List<Products> findByProdName(String prodName) {
		log.info("Inside find by prod name Service");
		return krogerProdRepo.findByProdName(prodName);

	}

	public Products findByProdDesc(String prodDesc) {
		log.info("Inside find by price service");
		return krogerProdRepo.findByProdDesc(prodDesc);
	}

}
