package tests.unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.User;

public class UserTest {

	User user;

	@Before
	public void setup() throws Exception {
		user = new User(0,"jacob","password");
	}
	
	@Test
	public void userGettersTest() {
		int id = user.getUserid();
		String username = user.getUsername();
		String password = user.getPassword();
		assertEquals(0, id);
		assertEquals("jacob", username);
		assertEquals("password", password);
	}
	
	@Test
	public void userSettersTest() {
		user.setUserid(1);
		user.setUsername("jacobperks");
		user.setPassword("password2");
		int id = user.getUserid();
		String username = user.getUsername();
		String password = user.getPassword();
		assertEquals(1, id);
		assertEquals("jacobperks", username);
		assertEquals("password2", password);
	}
	
	@Test 
	public void userToStringTest() {
		String userString = user.toString();
		assertEquals("[0,jacob,password]",userString);
	}

}
