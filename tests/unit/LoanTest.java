package tests.unit;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Loan;

public class LoanTest {

	Loan loan;
	Date date;
	
	@Before
	public void setup() throws Exception {
		date = new Date();
		loan = new Loan(0,"9781442668584","1",date,"0", 0);
	}

	@Test
	public void loanGettersTest() {
		int userid = loan.getUserid();
		String isbn = loan.getIsbn() ;
		String copynumber = loan.getCopynumber();
		String renewstate = loan.getRenewstate();
		int loanid = loan.getLoanid(); 
		assertEquals(0, userid);
		assertEquals("9781442668584", isbn);
		assertEquals("1", copynumber);
		assertEquals(date, loan.getDate());
		assertEquals("0", renewstate);
		assertEquals(0, loanid);
	}
	
	@Test
	public void loanSettersTest() {
		loan.setUserid(1);
		int userid = loan.getUserid();
		loan.setIsbn("9781442668585");
		String isbn = loan.getIsbn();
		loan.setCopynumber("2");
		String copynumber = loan.getCopynumber();
		Date date = new Date();
		loan.setDate(date);
		loan.setRenewstate("1");
		String renewstate = loan.getRenewstate();
		loan.setLoanid(1);
		int loanid = loan.getLoanid(); 
		assertEquals(1,userid);
		assertEquals("9781442668585",isbn);
		assertEquals("2",copynumber);
		assertEquals(date, loan.getDate());
		assertEquals("1",renewstate);
		assertEquals(1,loanid);
	}
	
	@Test
	public void loanToStringTest() {
		String loanString = loan.toString();
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String formatted = format1.format(date);
		assertEquals("[0,9781442668584,1,"+formatted+",0,0]",loanString);
	}

}
