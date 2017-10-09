package tests.acceptance;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class returnLoan {

	@Test
	public void validTest1() {
		String result = "";
		InputHandler inputHandler = new InputHandler();
		OutputHandler outputHandler = new OutputHandler();
		Output o = new Output("",0);
		String output = "";
		ServerOutput oo = new ServerOutput(output,o.getState());
		
		oo = inputHandler.processInput("USER", InputHandler.USERLOGIN);
		o = outputHandler.userLogin("Sun@carleton.ca,sun");
		
		oo = inputHandler.processInput(o.getOutput(), o.getState());
		
		if (oo.getState() == InputHandler.USER) {
			String input = "sun@carleton.ca,9781442616899,1";
			o = outputHandler.returnBook(input);
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
		
		oo = inputHandler.processInput("USER", InputHandler.USERLOGIN);
		o = outputHandler.userLogin("Michelle@carleton.ca,michelle");
		
		oo = inputHandler.processInput(o.getOutput(), o.getState());
		
		if (oo.getState() == InputHandler.USER) {
			String input = "michelle@carleton.ca,9781442616899,1";
			o = outputHandler.returnBook(input);
    		oo.setOutput(o.getOutput());
            oo.setState(o.getState());
            result = o.getOutput();
		} else {
			result = "Failure!";
		}
		assertEquals("The Loan Does Not Exist!", result);
	}

}
