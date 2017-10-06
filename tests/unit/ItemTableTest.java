package tests.unit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.tables.ItemTable;
import server.logic.model.Item;

public class ItemTableTest {

	ItemTable itemTable;
	
	@Before
	public void setup() throws Exception {
		itemTable = ItemTable.getInstance();
	}
	
	@Test 
	public void getItemTableTest() {
		List<Item> itemListActual = itemTable.getItemTable();
		String[] ISBNListExpected = new String[]{"9781442668584","9781442616899","9781442667181","9781611687910","9781317594277"};
		for (int i = 0; i < itemListActual.size(); i++) {
			assertEquals(i, itemListActual.get(i).getItemid());
			assertEquals(ISBNListExpected[i], itemListActual.get(i).getISBN());
			assertEquals("1",itemListActual.get(i).getCopynumber());
		}
	}
	
	@Test
	public void lookupTestPass() {
		String[] ISBNList=new String[]{"9781442668584","9781442616899","9781442667181","9781611687910"};
    	String[] cnList=new String[]{"1","1","1","1"};
    	for (int i = 0; i < ISBNList.length; i++) {
			assertEquals(true, itemTable.lookup(ISBNList[i],cnList[i]));
		}
	}
	
	@Test
	public void lookupTestFail() {
		assertEquals(false, itemTable.lookup("9781442668584","0"));
		assertEquals(false, itemTable.lookup("978144266858","1"));
	}
}
