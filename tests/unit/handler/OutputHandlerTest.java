package tests.unit.handler;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.model.Output;
import server.logic.handler.OutputHandler;
import server.logic.model.Title;
import server.logic.model.User;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class OutputHandlerTest {

	OutputHandler outputHandler;
	UserTable userTable;
	TitleTable titleTable;

	@Before
	public void setup() throws Exception {
		outputHandler = new OutputHandler();
		userTable = UserTable.getInstance();
		titleTable = TitleTable.getInstance();
	}
	
	@Test
	public void isIntegerTest() {
		assertEquals(false, OutputHandler.isInteger("10"));
		assertEquals(false, OutputHandler.isInteger("hello"));
		assertEquals(true, OutputHandler.isInteger("9781442668584"));
	}
	
	@Test
	public void isNumberTest() {
		assertEquals(false, outputHandler.isNumber("abcd10"));
		assertEquals(false, outputHandler.isNumber("hello"));
		assertEquals(true, outputHandler.isNumber("9781442668584"));
		assertEquals(true, outputHandler.isNumber("2"));
	}
	
	@Test
	public void createUserPass() {
		Output expected = new Output("Success!",2);
		Output actual = outputHandler.createUser("jacob@carleton.ca,password123");
		assertEquals(expected.getState(), actual.getState());
		assertEquals(expected.getOutput(), actual.getOutput());
		List<User> userListActual = userTable.getUserTable();
		assertEquals("jacob@carleton.ca", userListActual.get(userListActual.size()-1).getUsername());
		assertEquals("password123", userListActual.get(userListActual.size()-1).getPassword());
	}
	
	@Test
	public void createUserFail() {
		List<User> userList = userTable.getUserTable();
		for (int i = 0; i < userList.size(); i++){
			Output expected = new Output("The User Already Exists!",2);
			String username = userList.get(i).getUsername();
			String password = userList.get(i).getPassword();
			Output actual = outputHandler.createUser(username+","+password);
			assertEquals(expected.getState(), actual.getState());
			assertEquals(expected.getOutput(), actual.getOutput());
		}
	}
	
	@Test
	public void createTitlePass() {
		Output expected = new Output("Success!",2);
		Output actual = outputHandler.createTitle("9780736692427,Animal Farm");
		assertEquals(expected.getState(), actual.getState());
		assertEquals(expected.getOutput(), actual.getOutput());
		List<Title> titleListActual = titleTable.getTitleTable();
		assertEquals("9780736692427", titleListActual.get(titleListActual.size()-1).getISBN());
		assertEquals("Animal Farm", titleListActual.get(titleListActual.size()-1).getBooktitle());
	}
	
	@Test
	public void createTitleFail() {
		List<Title> titleList = titleTable.getTitleTable();
		for (int i = 0; i < titleList.size(); i++){
			Output expected = new Output("The Title Already Exists!",2);
			String isbn = titleList.get(i).getISBN();
			String booktitle = titleList.get(i).getBooktitle();
			Output actual = outputHandler.createTitle(isbn+","+booktitle);
			assertEquals(expected.getState(), actual.getState());
			assertEquals(expected.getOutput(), actual.getOutput());
		}
	}
}
