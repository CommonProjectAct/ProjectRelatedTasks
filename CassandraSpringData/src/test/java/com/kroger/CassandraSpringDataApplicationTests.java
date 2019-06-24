package com.kroger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.kroger.Response.UserResponse;
import com.kroger.controller.KrogerController;
import com.kroger.model.Products;
import com.kroger.model.User;
import com.kroger.repository.KrogerProdRepo;
import com.kroger.service.KrogerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CassandraSpringDataApplicationTests {

	
	@Autowired
	KrogerController krogerController;
	
	@InjectMocks
	KrogerService krogerService;
	
	@MockBean
	KrogerProdRepo krogerProdRepo;
	
	@Autowired
	Products products;
	
	@Autowired
	User user;
	
	@Autowired
	User userres;
	
	
	@Before
	public void setup(){
	    MockitoAnnotations.initMocks(this); 
	}
	
	@Before
	public void userSetUp() {
		
		user.setFirst_name("Rahul");
		user.setLast_name("Kumar");
		user.setType("Prime");
		user.setUser_id("U6");
	}
	
	@Before
	public void prodSetUp() {
		
		products.setProd_id("M3");
		products.setProd_desc("IphoneXe");
		products.setProdname("Mobile");
		products.setProd_price(20000);
	}
	
	@Test
	public void findByUserIdTest() {
		
		UserResponse userResponse =krogerController.findByUserId("U1");
		
		assertEquals(0,userResponse.getStatus_code());
		assertEquals("Successfull",userResponse.getStatus_msg());
		
		Optional<User> user=(Optional<User>) userResponse.getResponse();
		assertEquals("Ajith",user.get().getFirst_name());
	}
	
	@Test
	public void saveUserTest() {
		
		UserResponse userResponse=krogerController.saveUser(user);
		
		assertEquals(0, userResponse.getStatus_code());
		assertEquals("Successful", userResponse.getStatus_msg());
		assertEquals("User id : U6 inserted successfully", userResponse.getResponse());
	}
	
	@Test
	public void deleteUserTest() {
		
		UserResponse userResponse =krogerController.deleteUser("U6");
		assertEquals(0, userResponse.getStatus_code());
		assertEquals("Successful", userResponse.getStatus_msg());
		assertEquals("User id : U6 deleted successfully", userResponse.getResponse());
		
	}
	
	
	  @Test 
	  public void updateUserTest() {
	  
		  krogerController.saveUser(user); 
		  UserResponse userResponse=krogerController.updateUser("U6","Non-Prime");
		  assertEquals(0,userResponse.getStatus_code());
		  assertEquals("Successful", userResponse.getStatus_msg());
		  assertEquals("User id : U6 Updated successfully", userResponse.getResponse());
	  
	  }
	 
	
	@Test
	public void findByMockTest() {
		krogerProdRepo = mock(KrogerProdRepo.class);
		when(krogerProdRepo.findByProd_desc("IphoneXe")).thenReturn(products);
		
		assertEquals("Mobile",krogerProdRepo.findByProd_desc("IphoneXe").getProdname());
		
	}
	
}
