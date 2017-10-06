package tests.unit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.tables.ItemTable;
import server.logic.tables.TitleTable;
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
	
	@Test
	public void createItemPass() {
		List<Item> itemList = itemTable.getItemTable();
		assertEquals(true, itemTable.createitem("9781442668584"));
		String ISBN = itemList.get(itemList.size()-1).getISBN();
		assertEquals("9781442668584", ISBN);
		assertEquals(true, itemTable.createitem("9781317594277"));
		ISBN = itemList.get(itemList.size()-1).getISBN();
		assertEquals("9781317594277", ISBN);
	}
	
	@Test
	public void createItemFail() {
		assertEquals(false, itemTable.createitem("9781442668585"));
	}
	
	@Test
	public void deleteAllTest() {
		TitleTable titleTable = TitleTable.getInstance();
		for (int i = 0; i < titleTable.getTitleTable().size(); i++) {
			String isbn = titleTable.getTitleTable().get(i).getISBN();
			itemTable.deleteAll(isbn);
		}
		for (int i = 0; i < itemTable.getItemTable().size(); i++) {
			assertEquals("N/A", itemTable.getItemTable().get(i).getISBN());
			assertEquals("N/A", itemTable.getItemTable().get(i).getCopynumber());
		}
	}
}
