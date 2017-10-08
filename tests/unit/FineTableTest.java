package tests.unit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Fine;
import server.logic.model.User;
import server.logic.tables.FineTable;
import server.logic.tables.UserTable;

public class FineTableTest {

	FineTable fineTable;
	UserTable userTable;
	
	@Before
	public void setup() throws Exception {
		fineTable = FineTable.getInstance();
		userTable = UserTable.getInstance();
	}
	
	@Test 
	public void getFineTableTest() {
		List<Fine> fineListActual = fineTable.getFineTable();
		assertEquals(0, fineListActual.get(0).getUserid());
		assertEquals(5, fineListActual.get(0).getFee());
		assertEquals(4, fineListActual.get(1).getUserid());
		assertEquals(0, fineListActual.get(1).getFee());
	}
	
	@Test
	public void lookupTestPass() {
		List<User> userListActual = userTable.getUserTable();
		for (int i = 1; i < userListActual.size(); i++){
			assertEquals(true, fineTable.lookup(i));
		}
		assertEquals(true, fineTable.lookup(10));
	}
	
	@Test
	public void lookupTestFail() {
		assertEquals(false, fineTable.lookup(0));
	}
	
	@Test
	public void payFineTestPass() {
		assertEquals("success", fineTable.payfine(0));
	}

}
