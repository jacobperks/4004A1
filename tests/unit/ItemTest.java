package tests.unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Item;

public class ItemTest {

	Item item;
	
	@Before
	public void setup() throws Exception {
		item = new Item(0,"9781442668584","1");
	}
	
	@Test
	public void itemGettersTest() {
		String isbn = item.getISBN();
		int id = item.getItemid();
		String copynumber = item.getCopynumber();
		assertEquals("9781442668584", isbn);
		assertEquals(0, id);
		assertEquals("1",copynumber);
	}
	
	@Test
	public void itemSettersTest() {
		item.setISBN("9781442668585");
		item.setItemid(1);
		item.setCopynumber("2");
		String isbn = item.getISBN();
		String copynumber = item.getCopynumber();
		int id = item.getItemid();
		assertEquals("9781442668585", isbn);
		assertEquals(1, id);
		assertEquals("2", copynumber);
	}
	
	@Test 
	public void itemToStringTest() {
		String itemString = item.toString();
		assertEquals("[0,9781442668584,1]",itemString);
	}

}
