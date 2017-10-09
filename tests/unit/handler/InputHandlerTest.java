package tests.unit.handler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;


public class InputHandlerTest {

	InputHandler inputHandler;
	
	@Before
	public void setup() throws Exception {
		inputHandler = new InputHandler();
	}
	
	@Test
	public void processInputPass() {
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		oo = inputHandler.processInput("clerk", InputHandler.FINISHWAITING);
		assertEquals("Please Input The Password:", oo.getOutput());
		assertEquals(InputHandler.CLERKLOGIN, oo.getState());
		
		oo = inputHandler.processInput("admin", oo.getState());
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item,Monitor.", oo.getOutput());
		assertEquals(InputHandler.CLERK, oo.getState());
		
		oo = inputHandler.processInput("", InputHandler.FINISHWAITING);
		assertEquals("Who Are you?Clerk or User?", oo.getOutput());
		assertEquals(InputHandler.FINISHWAITING, oo.getState());
	}
	
	@Test
	public void processInputFail() {
		String output = "";
		Output o = new Output("",0);
		ServerOutput oo = new ServerOutput(output,o.getState());
		oo = inputHandler.processInput("", -1);
		assertEquals("", oo.getOutput());
		assertEquals(InputHandler.WAITING, oo.getState());
	}

}
