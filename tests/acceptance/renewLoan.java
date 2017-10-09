package tests.acceptance;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class renewLoan {

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
			o = outputHandler.renew(input);
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
			String input = "zhibo@carleton.ca,9781442668584,1";
			o = outputHandler.renew(input);
    		oo.setOutput(o.getOutput());
            oo.setState(o.getState());
            if (oo.getState() == InputHandler.PAYFINE) {
            	o = outputHandler.payFine("zhibo@carleton.ca");
            	oo.setOutput(o.getOutput());
                oo.setState(o.getState());
                if (oo.getState() == InputHandler.USER) {
        			o = outputHandler.renew(input);
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
		o = outputHandler.userLogin("Sun@carleton.ca,sun");
		
		oo = inputHandler.processInput(o.getOutput(), o.getState());
		
		if (oo.getState() == InputHandler.USER) {
			String input = "Sun@carleton.ca,9781442616899,1";
			o = outputHandler.renew(input);
    		oo.setOutput(o.getOutput());
            oo.setState(o.getState());
            if (oo.getState() == InputHandler.USER) {
            	o = outputHandler.renew(input);
        		oo.setOutput(o.getOutput());
                oo.setState(o.getState());
                result = o.getOutput();
            }
		} else {
			result = "Failure!";
		}
		assertEquals("Renewed Item More Than Once for the Same Loan!", result);
	}

}
