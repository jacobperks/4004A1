package tests.unit.handler;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.model.Output;
import server.logic.handler.OutputHandler;
import server.logic.model.Item;
import server.logic.model.Title;
import server.logic.model.User;
import server.logic.tables.ItemTable;
import server.logic.tables.LoanTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class OutputHandlerTest {

	OutputHandler outputHandler;
	UserTable userTable;
	TitleTable titleTable;
	ItemTable itemTable;

	@Before
	public void setup() throws Exception {
		outputHandler = new OutputHandler();
		userTable = UserTable.getInstance();
		titleTable = TitleTable.getInstance();
		itemTable = ItemTable.getInstance();
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
	
	@Test
	public void createItemPass() {
		List<Item> itemList = itemTable.getItemTable();
		Output expected = new Output("Success!",2);
		Output actual = outputHandler.createItem("9781442668584");
		assertEquals(expected.getState(), actual.getState());
		assertEquals(expected.getOutput(), actual.getOutput());
		String ISBN = itemList.get(itemList.size()-1).getISBN();
		assertEquals("9781442668584", ISBN);
	}
	
	@Test
	public void createItemFail() {
		Output expected = new Output("The Title Does Not Exists!",2);
		Output actual = outputHandler.createItem("9781442668585");
		assertEquals(expected.getState(), actual.getState());
		assertEquals(expected.getOutput(), actual.getOutput());
	}
	
	@Test
	public void deleteUserPass() {
		Output expected = new Output("Success!",2);
		for (int i = 1; i < userTable.getUserTable().size()-1; i++) {
			Output actual = outputHandler.deleteUser(userTable.getUserTable().get(i).getUsername());
			assertEquals(expected.getState(), actual.getState());
			assertEquals(expected.getOutput(), actual.getOutput());
		}
		for (int i = 1; i < userTable.getUserTable().size()-1; i++) {
			assertEquals("N/A", userTable.getUserTable().get(i).getUsername());
		}
	}
	
	@Test
	public void deleteUserFail() {
		Output expected = new Output("Outstanding Fee Exists!",2);
		Output actual = outputHandler.deleteUser(userTable.getUserTable().get(0).getUsername());
		assertEquals(expected.getState(), actual.getState());
		assertEquals(expected.getOutput(), actual.getOutput());
		
		expected = new Output("Active Loan Exists!",2);
		actual = outputHandler.deleteUser(userTable.getUserTable().get(4).getUsername());
		assertEquals(expected.getState(), actual.getState());
		assertEquals(expected.getOutput(), actual.getOutput());
		
		expected = new Output("The User Does Not Exist!",7);
		actual = outputHandler.deleteUser("jacob@carleton.ca");
		assertEquals(expected.getState(), actual.getState());
		assertEquals(expected.getOutput(), actual.getOutput());

	}
	
	@Test
	public void deleteTitlePass() {
		Output expected = new Output("Success!",2);
		for (int i = 2; i < titleTable.getTitleTable().size(); i++) {
			Output actual = outputHandler.deleteTitle(titleTable.getTitleTable().get(i).getISBN());
			assertEquals(expected.getState(), actual.getState());
			assertEquals(expected.getOutput(), actual.getOutput());
		}
	}
	
	@Test
	public void deleteTitleFail() {
		Output expected = new Output("Active Loan Exists!",2);
		Output actual = outputHandler.deleteTitle(titleTable.getTitleTable().get(0).getISBN());
		assertEquals(expected.getState(), actual.getState());
		assertEquals(expected.getOutput(), actual.getOutput());
		
		actual = outputHandler.deleteTitle(titleTable.getTitleTable().get(1).getISBN());
		assertEquals(expected.getState(), actual.getState());
		assertEquals(expected.getOutput(), actual.getOutput());
		
		expected = new Output("The Title Does Not Exist!",2);
		actual = outputHandler.deleteTitle("1234567891011");
		assertEquals(expected.getState(), actual.getState());
		assertEquals(expected.getOutput(), actual.getOutput());
	}
	
	@Test
	public void deleteItemPass() {
		Output expected = new Output("Success!",2);
		for (int i = 2; i < itemTable.getItemTable().size(); i++) {
			Output actual = outputHandler.deleteItem(itemTable.getItemTable().get(i).getISBN()+","+itemTable.getItemTable().get(i).getCopynumber());
			assertEquals(expected.getState(), actual.getState());
			assertEquals(expected.getOutput(), actual.getOutput());
		}
	}
	
	@Test
	public void deleteItemFail() {
		LoanTable loanTable = LoanTable.getInstance();
		Output expected = new Output("Active Loan Exists!",2);
		for (int i = 0; i < loanTable.getLoanTable().size(); i++) {
			Output actual = outputHandler.deleteItem(itemTable.getItemTable().get(i).getISBN()+","+itemTable.getItemTable().get(i).getCopynumber());
			assertEquals(expected.getState(), actual.getState());
			assertEquals(expected.getOutput(), actual.getOutput());
		}
		expected = new Output("The Item Does Not Exist!",2);
		Output actual = outputHandler.deleteItem("1111111111111,1");
		assertEquals(expected.getState(), actual.getState());
		assertEquals(expected.getOutput(), actual.getOutput());
	}
}
