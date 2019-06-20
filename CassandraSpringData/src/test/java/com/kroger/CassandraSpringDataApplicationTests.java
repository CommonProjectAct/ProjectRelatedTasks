package com.kroger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
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
import com.kroger.model.User;
import com.kroger.service.KrogerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CassandraSpringDataApplicationTests {

	
	//@InjectMocks
	@Autowired
	KrogerController krogerController;
	
	//@MockBean
	@Autowired
	KrogerService krogerService;
	
	@Autowired
	User user;
	
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
	
	@Test
	public void findByUserIdTest() {
		
		Optional<User> userOptest=krogerController.findByUserId("U2");
		User usertest=userOptest.get();
		
		assertEquals(true,userOptest.isPresent());
		assertEquals("Praveen",usertest.getFirst_name());
		assertEquals("Non-Prime", usertest.getType());
	}
	
	@Test
	public void SaveUserTest() {
		
		krogerController.saveUser(user);
		assertEquals("Rahul", krogerController.findByUserId("U6").get().getFirst_name());
	}
	
	@Test(expected = NoSuchElementException.class )
	public void DeleteUserTest() {
		
		krogerController.deleteUser("U6");
		assertEquals("Rahul", krogerController.findByUserId("U6").get().getFirst_name());
		
	}
	
	
	
	/*
	 * KrogerService serviceMock = mock(KrogerService.class);
	 * when(serviceMock.findByUserId("U2")).thenReturn(Optional.of(user));
	 * Optional<User> userOptest=krogerController.findByUserId("U2"); User usertest
	 * = userOptest.get();
	 */

	/*
	 * @Autowired User user;
	 * 
	 * @Before public void setupUser() { user= new User(); user.setUser_id("U2");
	 * user.setFirst_name("Praveen"); user.setLast_name("Neelamegam");
	 * user.setType("Prime"); }
	 */
	
	
}
