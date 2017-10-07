package tests.unit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.User;
import server.logic.tables.UserTable;

public class UserTableTest {

	UserTable userTable;
	
	@Before
	public void setup() throws Exception {
		userTable = UserTable.getInstance();
	}
	
	@Test 
	public void getUserTableTest() {
		List<User> userListActual = userTable.getUserTable();
    	String[] passwordList=new String[]{"Zhibo","Yu","Michelle","Kevin","Sun"};
    	String[] usernameList=new String[]{"Zhibo@carleton.ca","Yu@carleton.ca","Michelle@carleton.ca","Kevin@carleton.ca","Sun@carleton.ca"};
		for (int i = 0; i < userListActual.size(); i++) {
			assertEquals(i, userListActual.get(i).getUserid());
			assertEquals(usernameList[i], userListActual.get(i).getUsername());
			assertEquals(passwordList[i], userListActual.get(i).getPassword());
		}
	}
	
	@Test
	public void lookupUsernameTestPass() {
		String[] usernameList=new String[]{"Zhibo@carleton.ca","Yu@carleton.ca","Michelle@carleton.ca","Kevin@carleton.ca","Sun@carleton.ca"};
		for (int i = 0; i < usernameList.length; i++) {
			assertEquals(i, userTable.lookup(usernameList[i]));
		}
	}
	
	@Test
	public void lookupUsernameTestFail() {
		assertEquals(-1, userTable.lookup("Jacob@carleton.ca"));
	}
	
	@Test
	public void lookupIDTestPass() {
		String[] usernameList=new String[]{"Zhibo@carleton.ca","Yu@carleton.ca","Michelle@carleton.ca","Kevin@carleton.ca","Sun@carleton.ca"};
		for (int i = 0; i < usernameList.length; i++) {
			assertEquals(true, userTable.lookup(i));
		}
	}
	
	@Test
	public void lookupIDTestFail() {
		assertEquals(false, userTable.lookup(-1));
	}
}
