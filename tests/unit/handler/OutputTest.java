package tests.unit.handler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.model.Output;;

public class OutputTest {

	Output output;

	@Before
	public void setup() throws Exception {
		output = new Output("CREATEUSER",4);
	}
	
	@Test
	public void outputGettersTest() {
		String o = output.getOutput();
		int state = output.getState();
		assertEquals("CREATEUSER", o);
		assertEquals(4, state);
	}
	
	@Test
	public void outputSettersTest() {
		output.setOutput("WAITING");
		output.setState(0);
		String o = output.getOutput();
		int state = output.getState();
		assertEquals("WAITING", o);
		assertEquals(0, state);
	}
	
	@Test 
	public void outputToStringTest() {
		String outputString = output.toString();
		assertEquals("[CREATEUSER,4]", outputString);
	}

}
