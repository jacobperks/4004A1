package tests.unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Title;

public class TitleTest {

	Title title;
	
	@Before
	public void setup() throws Exception {
		title = new Title("9781442668584","By the grace of God" );
	}
	
	@Test
	public void titleGettersTest() {
		String isbn = title.getISBN();
		String booktitle = title.getBooktitle();
		assertEquals("9781442668584", isbn);
		assertEquals("By the grace of God", booktitle);
	}
	
	@Test
	public void titleSettersTest() {
		title.setISBN("9781442668585");
		title.setBooktitle("By the grace of God 2");
		String isbn = title.getISBN();
		String booktitle = title.getBooktitle();
		assertEquals("9781442668585", isbn);
		assertEquals("By the grace of God 2", booktitle);
	}
	
	@Test
	public void titleToStringTest() {
		String titleString = title.toString();
		assertEquals("[9781442668584,By the grace of God]",titleString);
	}

}
