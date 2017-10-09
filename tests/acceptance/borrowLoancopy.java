package tests.acceptance;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class borrowLoancopy {

	@Test
	public void validTest1() {
		String result = "";
		InputHandler inputHandler = new InputHandler();
		OutputHandler outputHandler = new OutputHandler();
		Output o = new Output("",0);
		String output = "";
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		oo = inputHandler.processInput("USER", InputHandler.USERLOGIN);
		o = outputHandler.userLogin("michelle@carleton.ca,michelle");
		
		oo = inputHandler.processInput(o.getOutput(), o.getState());
		
		if (oo.getState() == InputHandler.USER) {
			String input = "michelle@carleton.ca,9781442667181,1";
			o = outputHandler.borrow(input);
    		oo.setOutput(o.getOutput());
            oo.setState(o.getState());
            result = o.getOutput();
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
		
		oo = inputHandler.processInput("USER", InputHandler.USERLOGIN);
		o = outputHandler.userLogin("zhibo@carleton.ca,zhibo");
		
		oo = inputHandler.processInput(o.getOutput(), o.getState());
		
		if (oo.getState() == InputHandler.USER) {
			String input = "zhibo@carleton.ca,9781611687910,1";
			o = outputHandler.borrow(input);
    		oo.setOutput(o.getOutput());
            oo.setState(o.getState());
            if (oo.getState() == InputHandler.PAYFINE) {
            	o = outputHandler.payFine("zhibo@carleton.ca");
            	oo.setOutput(o.getOutput());
                oo.setState(o.getState());
                if (oo.getState() == InputHandler.USER) {
        			o = outputHandler.borrow(input);
            		oo.setOutput(o.getOutput());
                    oo.setState(o.getState());
                    result = o.getOutput();
                }
            }
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
		
		oo = inputHandler.processInput("USER", InputHandler.USERLOGIN);
		o = outputHandler.userLogin("michelle@carleton.ca,michelle");
		
		oo = inputHandler.processInput(o.getOutput(), o.getState());
		
		if (oo.getState() == InputHandler.USER) {
			String input = "michelle@carleton.ca,978144266858,1";
			o = outputHandler.borrow(input);
    		oo.setOutput(o.getOutput());
            oo.setState(o.getState());
            if (oo.getState() == InputHandler.BORROW) {
            	input = "michelle@carleton.ca,9781442668584,1";
            	o = outputHandler.borrow(input);
            	oo.setOutput(o.getOutput());
                oo.setState(o.getState());
                result = o.getOutput();
            }
		} else {
			result = "Failure!";
		}
		assertEquals("The Item is Not Available!", result);
	}
}
