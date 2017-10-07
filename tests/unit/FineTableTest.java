package tests.unit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Fine;
import server.logic.tables.FineTable;

public class FineTableTest {

	FineTable fineTable;
	
	@Before
	public void setup() throws Exception {
		fineTable = FineTable.getInstance();
	}
	
	@Test 
	public void getFineTableTest() {
		List<Fine> fineListActual = fineTable.getFineTable();
		assertEquals(0, fineListActual.get(0).getUserid());
		assertEquals(5, fineListActual.get(0).getFee());
		assertEquals(4, fineListActual.get(1).getUserid());
		assertEquals(0, fineListActual.get(1).getFee());
	}
}
