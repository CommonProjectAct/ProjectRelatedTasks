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

@RunWith(SpringRunner.class)
@SpringBootTest
public class CassandraConfigApplicationTests {
 
	@Autowired
	UserController userController;
	
	@Autowired
	User user;
	
	@Before
	public void userSetUp() {
		
		user.setFirst_name("Rahul");
		user.setLast_name("Kumar");
		user.setType("Prime");
		user.setUser_id("U6");
	}
	
	@Test
	public void findByUserId() {
		
		assertEquals("Praveen", userController.findByUserId("U2").getFirst_name());
	}
	
	@Test
	public void saveUserId() {
		userController.insertUser(user);
		assertEquals("Rahul",userController.findByUserId("U6").getFirst_name());
	}
	
	@Test
	public void updateUser() {
		userController.updateUser("U6", "Non-Prime");
		assertEquals("Non-Prime", userController.findByUserId("U6").getType());
	}
	
	@Test(expected = NullPointerException.class)
	public void deleteUserId() {
		userController.deleteByUserId("U6");
		assertEquals("Rahul",userController.findByUserId("U6").getFirst_name());
	}
	
	
	
}
