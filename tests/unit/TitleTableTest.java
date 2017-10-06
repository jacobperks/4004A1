package tests.unit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.tables.TitleTable;
import server.logic.model.Title;

public class TitleTableTest {

	TitleTable titleTable;
	
	@Before
	public void setup() throws Exception {
		titleTable = TitleTable.getInstance();
	}
	
	@Test 
	public void getTitleTableTest() {
		List<Title> titleListActual = titleTable.getTitleTable();
		String[] ISBNListExpected = new String[]{"9781442668584","9781442616899","9781442667181","9781611687910","9781317594277"};
		String[] booktitleListExpected = new String[]{"By the grace of God","Dante's lyric poetry ","Courtesy lost","Writing for justice","The act in context"};
		for (int i = 0; i < titleListActual.size(); i++) {
			assertEquals(booktitleListExpected[i], titleListActual.get(i).getBooktitle());
			assertEquals(ISBNListExpected[i], titleListActual.get(i).getISBN());
		}
	}

	@Test
	public void lookupTestPass() {
		String[] ISBNList = new String[]{"9781442668584","9781442616899","9781442667181","9781611687910","9781317594277"};
		for (int i = 0; i < ISBNList.length; i++) {
			assertEquals(true, titleTable.lookup(ISBNList[i]));
		}
	}
	
	@Test
	public void lookupTestFail() {
		assertEquals(false, titleTable.lookup("978144266858"));
	}

}
