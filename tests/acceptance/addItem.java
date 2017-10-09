package tests.acceptance;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class addItem {

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
			String input = "9781442617321";
			o = outputHandler.createItem(input);
    		oo.setOutput(o.getOutput());
            oo.setState(o.getState());
            if (oo.getState() == InputHandler.CREATETITLE) {
            	o = outputHandler.createTitle("9781442617321,Biography of Bob");
            	oo.setOutput(o.getOutput());
                oo.setState(o.getState());
                if (oo.getState() == InputHandler.CLERK) {
                	o = outputHandler.createItem(input);
            		oo.setOutput(o.getOutput());
                    oo.setState(o.getState());
                    result = o.getOutput();
                } else {
                	result = "Failure!";
                }
            } else {
            	result = "Failure!";
            }
		} else {
			result = "Failure!";
		}
		assertEquals("Success!", result);
	}
	
	@Test
	public void validTest2() {
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
			String input = "9781442668584";
			o = outputHandler.createItem(input);
    		oo.setOutput(o.getOutput());
            oo.setState(o.getState());
            result = o.getOutput();
		} else {
			result = "Failure!";
		}
		assertEquals("Success!", result);
	}
	
	@Test
	public void invalidTest3() {
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
			o = outputHandler.createItem(input);
    		oo.setOutput(o.getOutput());
            oo.setState(o.getState());
            result = o.getOutput();
		} else {
			result = "Failure!";
		}
		assertEquals("Your input should in this format:'ISBN',ISBN should be a 13-digit number", result);
	}
	
}
