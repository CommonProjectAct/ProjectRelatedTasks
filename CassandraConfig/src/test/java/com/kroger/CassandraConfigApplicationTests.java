package com.kroger;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.kroger.controller.UserController;
import com.kroger.model.User;
import com.kroger.response.UserResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CassandraConfigApplicationTests {
 
	@Autowired
	UserController userController;
	
	@Autowired
	User user;
	
	@Before
	public void userSetUp() {
		
		user.setFirstName("Rahul");
		user.setLastName("Kumar");
		user.setType("Prime");
		user.setUserId("U6");
	}
	
	@Test
	public void findByUserId() {
		UserResponse userResponse =userController.findByUserId("U2");
		assertEquals(0,userResponse.getStatusCode());
		assertEquals("Fetch is Successful",userResponse.getStatusMsg());
	}
	
	@Test
	public void saveUserId() {
		
		UserResponse userResponse=userController.insertUser(user);
		assertEquals(0, userResponse.getStatusCode());
		assertEquals("Insertion is Successful", userResponse.getStatusMsg());
	}
	
	@Test
	public void updateUser() {
		
		userController.insertUser(user);
		UserResponse userResponse=userController.findByUserId("U6");
		userController.updateUser("U6", "Non-Prime");
		assertEquals(0,userResponse.getStatusCode());
		assertEquals("Update is Successful", userResponse.getStatusMsg());
	}
	
	@Test
	public void deleteUserId() {
		UserResponse userResponse =userController.deleteByUserId("U6");
		assertEquals(0, userResponse.getStatusCode());
		assertEquals("Deletion is Successful", userResponse.getStatusMsg());
	}
	
	
	
}
