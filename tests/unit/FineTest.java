package tests.unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Fine;

public class FineTest {

	Fine fine;

	@Before
	public void setup() throws Exception {
		fine = new Fine(0,5);
	}
	
	@Test
	public void fineGettersTest() {
		int id = fine.getUserid();
		int fee = fine.getFee();
		assertEquals(0, id);
		assertEquals(5, fee);
	}
	
	@Test
	public void userSettersTest() {
		fine.setUserid(1);
		fine.setFee(10);
		int id = fine.getUserid();
		int fee = fine.getFee();
		assertEquals(1, id);
		assertEquals(10, fee);
	}
	
	@Test 
	public void fineToStringTest() {
		String fineString = fine.toString();
		assertEquals("[0,5]",fineString);
	}

}
