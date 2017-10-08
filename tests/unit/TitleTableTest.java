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
	
	@Test
	public void createTitlePass() {
		assertEquals(true, titleTable.createtitle("9780736692427", "Animal Farm"));
		List<Title> titleListActual = titleTable.getTitleTable();
		assertEquals("9780736692427", titleListActual.get(titleListActual.size()-1).getISBN());
		assertEquals("Animal Farm", titleListActual.get(titleListActual.size()-1).getBooktitle());
	}
	
	@Test
	public void createTitleFail() {
		List<Title> titleList = titleTable.getTitleTable();
		for (int i = 0; i < titleList.size(); i++){
			String isbn = titleList.get(i).getISBN();
			String booktitle = titleList.get(i).getBooktitle();
			assertEquals(false, titleTable.createtitle(isbn, booktitle));
		}
	}
	
	@Test
	public void deletePass() {
		for (int i = 2; i < titleTable.getTitleTable().size(); i++) {
			assertEquals("success", titleTable.delete(titleTable.getTitleTable().get(i).getISBN()));
		}
	}
	
	@Test
	public void deleteFail() {
		assertEquals("Active Loan Exists", titleTable.delete(titleTable.getTitleTable().get(0).getISBN()));
		assertEquals("Active Loan Exists", titleTable.delete(titleTable.getTitleTable().get(1).getISBN()));
		assertEquals("The Title Does Not Exist", titleTable.delete("1234"));
	}
	
	@Test
	public void toStringTest() {
		String titles = titleTable.toString();
		assertEquals("[By the grace of God,Dante's lyric poetry ,Courtesy lost,Writing for justice,The act in context]", titles);
		
	}

}
