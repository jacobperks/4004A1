package tests.unit.handler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;

public class OutputHandlerTest {

	OutputHandler outputHandler;

	@Before
	public void setup() throws Exception {
		outputHandler = new OutputHandler();
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

}
