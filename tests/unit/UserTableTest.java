package tests.unit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
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
	
	@After
	public void teardown() throws Exception {
		List<User> userListActual = userTable.getUserTable();
    	String[] passwordList=new String[]{"Zhibo","Yu","Michelle","Kevin","Sun"};
    	String[] usernameList=new String[]{"Zhibo@carleton.ca","Yu@carleton.ca","Michelle@carleton.ca","Kevin@carleton.ca","Sun@carleton.ca"};
    	
    	while (userListActual.size() > 5) {
    		userListActual.remove(userListActual.size()-1);
    	}
    	
    	for(int i=0;i<usernameList.length;i++){
			userListActual.get(i).setUsername(usernameList[i]);
			userListActual.get(i).setPassword(passwordList[i]);
		}
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
		assertEquals(-1, userTable.lookup("Zhibo1@carleton.ca"));
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
	
	@Test
	public void createUserPass() {
		assertEquals(true, userTable.createuser("jacob@carleton.ca","password123"));
		List<User> userListActual = userTable.getUserTable();
		assertEquals("jacob@carleton.ca", userListActual.get(userListActual.size()-1).getUsername());
		assertEquals("password123", userListActual.get(userListActual.size()-1).getPassword());
	}
	
	@Test
	public void createUserFail() {
		List<User> userList = userTable.getUserTable();
		for (int i = 0; i < userList.size(); i++){
			String username = userList.get(i).getUsername();
			String password = userList.get(i).getPassword();
			assertEquals(false, userTable.createuser(username, password));
		}
	}
	
	@Test
	public void deletePass() {
		for (int i = 1; i < userTable.getUserTable().size()-1; i++) {
			assertEquals("success", userTable.delete(i));
		}
		for (int i = 1; i < userTable.getUserTable().size()-1; i++) {
			assertEquals("N/A", userTable.getUserTable().get(i).getUsername());
		}
	}
	
	@Test
	public void deleteFail() {
		assertEquals("Outstanding Fee Exists", userTable.delete(0));
		assertEquals("Active Loan Exists", userTable.delete(4));
		assertEquals("The User Does Not Exist", userTable.delete(10));
	}
	
	@Test
	public void checkUserPass() {
		List<User> userList = userTable.getUserTable();
		for (int i = 0; i < userList.size(); i++){
			String username = userList.get(i).getUsername();
			String password = userList.get(i).getPassword();
			assertEquals(0, userTable.checkUser(username, password));
		}
	}
	
	@Test
	public void checkUserFail() {
		List<User> userList = userTable.getUserTable();
		assertEquals(1, userTable.checkUser(userList.get(0).getUsername(), "hello"));
		assertEquals(2,userTable.checkUser("zhibo1@carleton.ca", "hello"));
	}
	
	@Test
	public void toStringTest() {
		String users = userTable.toString();
		assertEquals("[Zhibo@carleton.ca,Yu@carleton.ca,Michelle@carleton.ca,Kevin@carleton.ca,Sun@carleton.ca]", users);
		
	}
}
