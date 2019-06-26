package com.kroger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import com.kroger.controller.KrogerController;
import com.kroger.model.Products;
import com.kroger.model.User;
import com.kroger.repository.KrogerProdRepo;
import com.kroger.response.UserResponse;
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
		
		user.setFirstName("Rahul");
		user.setLastName("Kumar");
		user.setType("Prime");
		user.setUserId("U6");
	}
	
	@Before
	public void prodSetUp() {
		
		products.setProdId("M3");
		products.setProdDesc("IphoneXe");
		products.setProdName("Mobile");
		products.setProdPrice(20000);
	}
	
	@Test
	public void findByUserIdTest() {
		
		UserResponse userResponse =krogerController.findByUserId("U1");
		
		assertEquals(0,userResponse.getStatusCode());
		assertEquals("Successful",userResponse.getStatusMsg());
		
		Optional<User> user=(Optional<User>) userResponse.getResponse();
		assertEquals("Praveen",user.get().getFirstName());
		
		UserResponse userResponseRes =krogerController.findByUserId("U100");
		
		assertEquals(404,userResponseRes.getStatusCode());
		assertEquals("Not Found",userResponseRes.getStatusMsg());
	}
	
	@Test
	public void saveUserTest() {
		
		UserResponse userResponse=krogerController.saveUser(user);
		
		assertEquals(0, userResponse.getStatusCode());
		assertEquals("Successful", userResponse.getStatusMsg());
		assertEquals("User id : U6 inserted successfully", userResponse.getResponse());
	}
	
	@Test
	public void deleteUserTest() {
		
		UserResponse userResponse =krogerController.deleteUser("U6");
		assertEquals(0, userResponse.getStatusCode());
		assertEquals("Successful", userResponse.getStatusMsg());
		assertEquals("User id : U6 deleted successfully", userResponse.getResponse());
		
		UserResponse userResponseRes =krogerController.deleteUser("U600");
		assertEquals(404, userResponseRes.getStatusCode());
		assertEquals("Not Found", userResponseRes.getStatusMsg());
		assertEquals("User id : U600 Not Exist", userResponseRes.getResponse());
		
	}
	
	
	  @Test 
	  public void updateUserTest() {
	  
		  krogerController.saveUser(user); 
		  UserResponse userResponse=krogerController.updateUser("U6","Non-Prime");
		  assertEquals(0,userResponse.getStatusCode());
		  assertEquals("Successful", userResponse.getStatusMsg());
		  assertEquals("User id : U6 Updated successfully", userResponse.getResponse());
		  
		  UserResponse userResponseRes =krogerController.deleteUser("U600");
			assertEquals(404, userResponseRes.getStatusCode());
			assertEquals("Not Found", userResponseRes.getStatusMsg());
			assertEquals("User id : U600 Not Exist", userResponseRes.getResponse());
	  
	  }
	  
	  @Test
	  public void findAllUserTest() {
		 UserResponse userResponse= krogerController.findAllUser();
		 
		 assertEquals(0, userResponse.getStatusCode());
		 assertEquals("Successful", userResponse.getStatusMsg());
		 assertNotNull(userResponse.getResponse());
	  }
	  
	 
	
	@Test
	public void findByMockTest() {
		krogerProdRepo = mock(KrogerProdRepo.class);
		when(krogerProdRepo.findByProdDesc("IphoneXe")).thenReturn(products);
		
		assertEquals("Mobile",krogerProdRepo.findByProdDesc("IphoneXe").getProdName());
		
	}
	
}
