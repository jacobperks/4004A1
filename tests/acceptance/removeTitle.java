package tests.acceptance;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class removeTitle {

	@Test
	public void validTest1() {
		String result = "";
		InputHandler inputHandler = new InputHandler();
		OutputHandler outputHandler = new OutputHandler();
		Output o = new Output("",0);
		String output = "";
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		oo = inputHandler.processInput("CLERK", InputHandler.CLERKLOGIN);
		o = outputHandler.clerkLogin("admin");
		
		oo = inputHandler.processInput(o.getOutput(), o.getState());
		
		if (oo.getState() == InputHandler.CLERK) {
			String input = "9781611687910";
			o = outputHandler.deleteTitle(input);
    		oo.setOutput(o.getOutput());
            oo.setState(o.getState());
            result = o.getOutput();
		} else {
			result = "Failure!";
		}
		assertEquals("Success!", result);
	}
	
	@Test
	public void invalidTest2() {
		String result = "";
		InputHandler inputHandler = new InputHandler();
		OutputHandler outputHandler = new OutputHandler();
		Output o = new Output("",0);
		String output = "";
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		oo = inputHandler.processInput("CLERK", InputHandler.CLERKLOGIN);
		o = outputHandler.clerkLogin("admin");
		
		oo = inputHandler.processInput(o.getOutput(), o.getState());
		
		if (oo.getState() == InputHandler.CLERK) {
			String input = "978144266858";
			o = outputHandler.deleteTitle(input);
    		oo.setOutput(o.getOutput());
            oo.setState(o.getState());
            if (oo.getState() == InputHandler.DELETETITLE) {
            	input = "9781442668584";
    			o = outputHandler.deleteTitle(input);
        		oo.setOutput(o.getOutput());
                oo.setState(o.getState());
                result = o.getOutput();
            }
		} else {
			result = "Failure!";
		}
		assertEquals("Active Loan Exists!", result);
	}

}
